/*
    Copyright (c) 2020 Obeo. 
       All rights reserved. This program and the accompanying materials
       are made available under the terms of the Eclipse Public License v1.0
       which accompanies this distribution, and is available at
       http://www.eclipse.org/legal/epl-v10.html
        
        Contributors:
            Obeo - initial API and implementation
*/

/* global document, Office, Word */
var awesomplete;

Office.onReady(info => {
  if (info.host === Office.HostType.Word) {
    document.getElementById("sideload-msg").style.display = "none";
    document.getElementById("app-body").style.display = "flex";
    var expressionInput = document.getElementById("expression");
    awesomplete = new Awesomplete(expressionInput, {
      minChars: 0, 
      autoFirst: true,
      maxItems: 20,
      filter: function(text, input) {
        return true;
      },
      replace: applyReplacement
    });
    expressionInput.oninput = openProposals;
    expressionInput.addEventListener('awesomplete-close', function (event) {
      if (event.reason == "esc") {
        // TODO validate the input that will be applied
        validationClear();
        // TODO evaluate the input that will be applied
        resultClear();
      }
    }, false);
    expressionInput.addEventListener('awesomplete-highlight', function (event) {
      // TODO show documentation
    }, false);

    validationClear();
    resultClear();
  }
});

export function openProposals() {
  var genconfURIInput = document.getElementById("genconfURI");
  var expressionInput = document.getElementById("expression");
  var expression = expressionInput.value;
  var genconfURI = genconfURIInput.value;
  var offset = expressionInput.selectionStart;

  validate(expression);
  evaluate(expression);
  var ajax = new XMLHttpRequest();
  ajax.onreadystatechange = function() {
    if (this.readyState == 4) {
      if (this.status == 200) {
        awesomplete.list = JSON.parse(this.responseText);
        awesomplete.open();
      } else if (this.status == 400) {
        validationError(this.responseText);
      }
    }
  };
  ajax.open("GET", "/rest?command=proposal&genconfURI=" + encodeURI(genconfURI) + "&expression=" + encodeURI(expression) + "&offset=" + offset, true);
  ajax.send();
}

export function applyReplacement(text) {
  var genconfURIInput = document.getElementById("genconfURI");
  var expressionInput = document.getElementById("expression");
  var expression = expressionInput.value;
  var genconfURI = genconfURIInput.value;
  var offset = expressionInput.selectionStart;

  var ajax = new XMLHttpRequest();
  ajax.onreadystatechange = function() {
    if (this.readyState == 4) {
      if (this.status == 200) {
        awesomplete.input.value = this.responseText;
        validate(this.responseText);
        evaluate(this.responseText);
      } else if (this.status == 400) {
        validationError(this.responseText);
      }
    }
  };
  ajax.open("GET", "/rest?command=apply&genconfURI=" + encodeURI(genconfURI) + "&expression=" + encodeURI(expression) + "&offset=" + offset +"&completion=" + encodeURI(text), true);
  ajax.send();
}

export function validate(expression) {
  var genconfURIInput = document.getElementById("genconfURI");
  var genconfURI = genconfURIInput.value;

  var ajax = new XMLHttpRequest();
  ajax.onreadystatechange = function() {
    if (this.readyState == 4) {
      if (this.status == 200) {
        var messages = JSON.parse(this.responseText);
        if (messages && messages.length) {
          validationMessages(messages);
        } else {
          validationClear();
        }
      } else {
        validationClear();
      }
    } else if (this.status == 400) {
      validationError(this.responseText);
    }
  };
  ajax.open("GET", "/rest?command=validate&genconfURI=" + encodeURI(genconfURI) + "&expression=" + encodeURI(expression), true);
  ajax.send();
}

export function evaluate(expression) {
  var genconfURIInput = document.getElementById("genconfURI");
  var genconfURI = genconfURIInput.value;

  var ajax = new XMLHttpRequest();
  ajax.onreadystatechange = function() {
    if (this.readyState == 4) {
      if (this.status == 200) {
        showResult(this.responseText);
      } else {
        resultClear();
      }
    } else if (this.status == 400) {
      validationError(this.responseText);
    }
  };
  ajax.open("GET", "/rest?command=evaluate&genconfURI=" + encodeURI(genconfURI) + "&expression=" + encodeURI(expression), true);
  ajax.send();
}

export function validationMessages(messages) {
  var html = "";
  for (var i = 0; i < messages.length; i++) {
    var message = messages[i];
    var color;
    if (message.level == "ERROR") {
      color = "lightcoral";
    } else if (message.level == "WARNING") {
      color = "lightgoldenrodyellow";
    } else if (message.level == "INFO") {
      color = "lightblue";
    } else {
      color = "";
    }
    html += "<p style=\"background-color:" + color + "\"><img src=\"/assets/" + message.level + ".png\">" + message.message + " (" + message.start + ", " + message.end + ")</p>";
  }
  document.getElementById("validationDiv").innerHTML = html;
}

export function validationError(message) {
  var validationDiv = document.getElementById("validationDiv");
  validationDiv.style.backgroundColor = "lightcoral";
  validationDiv.innerHTML = "<p>" + message + "</p>";
}

export function validationWarning(message) {
  var validationDiv = document.getElementById("validationDiv");
  validationDiv.style.backgroundColor = "lightgoldenrodyellow";
  validationDiv.innerHTML = "<p>" + message + "</p>";
}

export function validationClear() {
  var validationDiv = document.getElementById("validationDiv");
  validationDiv.style.backgroundColor = "";
  validationDiv.innerHTML = "";
}

export function showResult(html) {
  var resultDiv = document.getElementById("resultDiv");
  resultDiv.style.backgroundColor = "lightblue";
  resultDiv.innerHTML = html;
}

export function resultClear() {
  showResult("");
}
