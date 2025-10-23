#!/bin/sh
# ====================================================================
# Copyright (c) 2024 Obeo
# This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0
#
# Contributors:
#    Obeo - initial API and implementation
# ====================================================================
echo -n "Removing -SNAPSHOT from pom.xml... "
find . -name "pom.xml" -type f -exec sed -i 's/-SNAPSHOT//g' {} \;
echo "done"
echo -n "Removing .qualifier from MANIFEST.MF... "
find . -name "MANIFEST.MF" -type f -exec sed -i 's/.qualifier//g' {} \;
echo "done"
echo -n "Removing .qualifier from feature.xml... "
find . -name "feature.xml" -type f -exec sed -i 's/.qualifier//g' {} \;
echo "done"

