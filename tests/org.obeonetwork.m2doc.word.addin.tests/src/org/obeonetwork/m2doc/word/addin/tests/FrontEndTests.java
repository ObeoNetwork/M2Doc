/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.word.addin.tests;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.obeonetwork.m2doc.word.addin.CompletionServer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

/**
 * Bundle tests.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class FrontEndTests {

    /**
     * The {@link CompletionServer}.
     */
    private static final CompletionServer SERVER = new CompletionServer();

    private static FirefoxDriver driver;

    private final String url = "http://localhost:12345/taskpane.html";

    private final String genconfURI = URI.createFileURI(new File("resources/nominal.genconf").getAbsolutePath())
            .toString();

    @BeforeClass
    public static void beforeClass() throws Exception {
        SERVER.start("localhost", 12345);
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void afterCass() throws Exception {
        SERVER.stop();
        driver.quit();
    }

    @Test
    public void noGenconfURI() {
        driver.navigate().to(url);

        // Start the add-in
        driver.findElement(By.id("startButton")).click();

        driver.findElement(By.id("expression")).sendKeys("se");

        assertEquals("genconfURI parameter is mandatory", driver.findElement(By.id("validationDiv")).getText());
        assertEquals("", driver.findElement(By.id("resultDiv")).getText());
    }

    @Test
    public void completionProposal() throws InterruptedException {
        driver.navigate().to(url);

        // Start the add-in
        driver.findElement(By.id("startButton")).click();

        driver.findElement(By.id("genconfURI")).sendKeys(genconfURI);
        driver.findElement(By.id("expression")).click();
        driver.findElement(By.id("expression")).sendKeys("se");
        Thread.sleep(2000);
        driver.findElement(By.id("expression")).sendKeys("l");
        Thread.sleep(1000);

        assertEquals(
                "[{\"documentation\":\"Variable self\",\"cursorOffset\":4,\"label\":\"self\",\"type\":\"Variable\",\"value\":\"self\"}]",
                driver.executeScript("return JSON.stringify(window.awesomplete._list)"));
        assertEquals(
                "<img style=\"vertical-align: middle; padding-right: 5px;\" src=\"assets/completion/Variable.gif\"><mark>sel</mark>f",
                driver.executeScript("return document.getElementById(\"awesomplete_list_1_item_0\").innerHTML"));
        assertEquals("Variable self",
                driver.executeScript("return document.getElementById(\"awesomplete_list_1\").lastChild.innerHTML"));
        assertEquals("Couldn't find the 'sel' variable (0, 3)", driver.findElement(By.id("validationDiv")).getText());
        assertEquals("null", driver.findElement(By.id("resultDiv")).getText());
    }

    @Test
    public void completionApply() throws InterruptedException {
        driver.navigate().to(url);

        // Start the add-in
        driver.findElement(By.id("startButton")).click();

        driver.findElement(By.id("genconfURI")).sendKeys(genconfURI);
        driver.findElement(By.id("expression")).click();
        driver.findElement(By.id("expression")).sendKeys("self.n");
        Thread.sleep(2000);
        driver.findElement(By.id("expression")).sendKeys("a");
        Thread.sleep(1000);

        assertEquals(
                "[{\"documentation\":\"EAttribute named name in ENamedElement(http://www.eclipse.org/emf/2002/Ecore)\",\"cursorOffset\":9,\"label\":\"name\",\"type\":\"EAttribute\",\"value\":\"name\"}]",
                driver.executeScript("return JSON.stringify(window.awesomplete._list)"));
        assertEquals("Feature na not found in EClass EPackage (4, 7)",
                driver.findElement(By.id("validationDiv")).getText());
        assertEquals("null", driver.findElement(By.id("resultDiv")).getText());

        driver.findElement(By.id("awesomplete_list_1_item_0")).click();
        Thread.sleep(1000);
        assertEquals("self.name", driver.findElement(By.id("expression")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("validationDiv")).getText());
        assertEquals("anydsl", driver.findElement(By.id("resultDiv")).getText());
    }

    @Test
    public void completionApplyCaretPosition() throws InterruptedException {
        driver.navigate().to(url);

        // Start the add-in
        driver.findElement(By.id("startButton")).click();

        driver.findElement(By.id("genconfURI")).sendKeys(genconfURI);
        driver.findElement(By.id("expression")).click();
        driver.findElement(By.id("expression")).sendKeys("self.ances");
        Thread.sleep(2000);
        driver.findElement(By.id("expression")).sendKeys("t");
        Thread.sleep(1000);

        driver.findElement(By.id("expression")).sendKeys(Keys.DOWN);
        driver.findElement(By.id("expression")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        assertEquals("self.ancestors()", driver.findElement(By.id("expression")).getAttribute("value"));
        assertEquals("15", driver.findElement(By.id("expression")).getAttribute("selectionStart"));
        assertEquals("15", driver.findElement(By.id("expression")).getAttribute("selectionEnd"));
    }

}
