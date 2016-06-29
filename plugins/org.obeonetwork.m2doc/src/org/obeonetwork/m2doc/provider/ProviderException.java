/*******************************************************************************
 * Copyright (c) 2016 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
/*******************************************************************************
* Logiciel créé en 2009 par la Caisse Nationale d'Assurance Vieillesse (Cnav) France.
* Logiciel protégé au sens de l'article L.112-2 13 du Code de la propriété intellectuelle (CPI).
* Tous les droits relatifs audit logiciel, sa documentation,
* y compris le matériel de conception préparatoire sont la propriété de la Cnav au sens du CPI.
* Ce fichier appartient a la suite d'outils SATURNE
* (Suite Applicative pour des Traitements Unifiés et Rationalisés fondés sur une Norme d'Echange).
* depot INPI en cours
*
* Ce logiciel et sa documentation sont proteges par la licence incluse.
* Vous devez avoir souscrit à une licence concedee par la Cnav
* pour installer et utiliser ce logiciel.
*
*
* This file is part of the SATURNE Tool Suite
* (Suite of Autonomous Tools for Unified and Rationalized Norms of Exchange)..
*
* This software and the attached documentation are protected by the included license.
* You must be in possession of a valid license conceded by the Cnav
* to install and use this software.
*
*******************************************************************************/

package org.obeonetwork.m2doc.provider;

/**
 * Exception used by instances of {@link IProvider} when a problem occurs when called.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class ProviderException extends Exception {

    /**
     * Generated UID.
     */
    private static final long serialVersionUID = 4537314267740681624L;

    /**
     * Constructor.
     */
    public ProviderException() {
    }

    /**
     * Message constructor for the exception.
     * 
     * @param msg
     *            the exception message
     */
    public ProviderException(String msg) {
        super(msg);
    }

    /**
     * Constructor with message and cause.
     * 
     * @param msg
     *            the exception message
     * @param cause
     *            the original exception.
     */
    public ProviderException(String msg, Exception cause) {
        super(msg, cause);
    }
}
