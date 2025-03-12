/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibPackage.java,v 1.2 2010/03/18 06:24:36 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *       DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *       
 *       Copyright 2003-2009 Sun Microsystems, Inc. All rights reserved.
 *       
 *       The contents of this file are subject to the terms of either the
 *       GNU General Public License Version 2 only ("GPL") or the Common
 *       Development and Distribution License("CDDL") (collectively, the
 *       "License").  You may not use this file except in compliance with
 *       the License. You can obtain a copy of the License at
 *       https://glassfish.dev.java.net/public/CDDL+GPL.html or
 *       glassfish/bootstrap/legal/LICENSE.txt.  See the License for the
 *       specific language governing permissions and limitations under the
 *       License.
 *       
 *       When distributing the software, include this License Header
 *       Notice in each file and include the License file at
 *       glassfish/bootstrap/legal/LICENSE.txt.  Sun designates this
 *       particular file as subject to the "Classpath" exception as
 *       provided by Sun in the GPL Version 2 section of the License file
 *       that accompanied this code.  If applicable, add the following
 *       below the License Header, with the fields enclosed by brackets []
 *       replaced by your own identifying information:
 *       "Portions Copyrighted [year] [name of copyright owner]"
 *       
 *       Contributor(s):
 *       
 *       If you wish your version of this file to be governed by only the
 *       CDDL or only the GPL Version 2, indicate your decision by adding
 *       "[Contributor] elects to include this software in this
 *       distribution under the [CDDL or GPL Version 2] license."  If you
 *       don't indicate a single choice of license, a recipient has the
 *       option to distribute your version of this file under either the
 *       CDDL, the GPL Version 2 or to extend the choice of license to its
 *       licensees as provided above.  However, if you add GPL Version 2
 *       code and therefore, elected the GPL Version 2 license, then the
 *       option applies only if the new code is made subject to such
 *       option by the copyright holder.
 *       
 *     
 * 
 * 
 *             <![CDATA[
 * 
 *             The XML Schema for the Tag Libraries in the JavaServer Faces
 *             Standard Facelets View Declaration Language (Facelets VDL)
 *             (Version 2.0).
 * 
 *             JSF 2.0 Facelet Tag Libraries that wish to conform to this
 *             schema must declare it in the following manner.
 * 
 *             <facelet-taglib xmlns="http://java.sun.com/xml/ns/javaee"
 *               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *               xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facelettaglibary_2_0.xsd"
 *               version="2.0">
 * 
 *             ...
 * 
 *             </facelet-taglib>
 * 
 *             The instance documents may indicate the published
 *             version of the schema using xsi:schemaLocation attribute
 *             for javaee namespace with the following location:
 * 
 *             http://java.sun.com/xml/ns/javaee/web-facelettaglibary_2_0.xsd
 * 
 *             ]]>
 * 
 *         
 * 
 * 
 *     
 * 
 * 
 *       DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *       
 *       Copyright 2003-2009 Sun Microsystems, Inc. All rights reserved.
 *       
 *       The contents of this file are subject to the terms of either the
 *       GNU General Public License Version 2 only ("GPL") or the Common
 *       Development and Distribution License("CDDL") (collectively, the
 *       "License").  You may not use this file except in compliance with
 *       the License. You can obtain a copy of the License at
 *       https://glassfish.dev.java.net/public/CDDL+GPL.html or
 *       glassfish/bootstrap/legal/LICENSE.txt.  See the License for the
 *       specific language governing permissions and limitations under the
 *       License.
 *       
 *       When distributing the software, include this License Header
 *       Notice in each file and include the License file at
 *       glassfish/bootstrap/legal/LICENSE.txt.  Sun designates this
 *       particular file as subject to the "Classpath" exception as
 *       provided by Sun in the GPL Version 2 section of the License file
 *       that accompanied this code.  If applicable, add the following
 *       below the License Header, with the fields enclosed by brackets []
 *       replaced by your own identifying information:
 *       "Portions Copyrighted [year] [name of copyright owner]"
 *       
 *       Contributor(s):
 *       
 *       If you wish your version of this file to be governed by only the
 *       CDDL or only the GPL Version 2, indicate your decision by adding
 *       "[Contributor] elects to include this software in this
 *       distribution under the [CDDL or GPL Version 2] license."  If you
 *       don't indicate a single choice of license, a recipient has the
 *       option to distribute your version of this file under either the
 *       CDDL, the GPL Version 2 or to extend the choice of license to its
 *       licensees as provided above.  However, if you add GPL Version 2
 *       code and therefore, elected the GPL Version 2 license, then the
 *       option applies only if the new code is made subject to such
 *       option by the copyright holder.
 *       
 *     
 * 
 * 
 * The following definitions that appear in the common
 * shareable schema(s) of J2EE deployment descriptors should be
 * interpreted with respect to the context they are included:
 * 
 * Deployment Component may indicate one of the following:
 *     j2ee application;
 *     application client;
 *     web application;
 *     enterprise bean;
 *     resource adapter;
 * 
 * Deployment File may indicate one of the following:
 *     ear file;
 *     war file;
 *     jar file;
 *     rar file;
 * 
 * 
 * 
 *    <div xmlns="http://www.w3.org/1999/xhtml">
 *     <h1>About the XML namespace</h1>
 * 
 *     <div class="bodytext">
 *      <p>
 *       This schema document describes the XML namespace, in a form
 *       suitable for import by other schema documents.
 *      </p>
 *      <p>
 *       See <a href="http://www.w3.org/XML/1998/namespace.html">
 *       http://www.w3.org/XML/1998/namespace.html</a> and
 *       <a href="http://www.w3.org/TR/REC-xml">
 *       http://www.w3.org/TR/REC-xml</a> for information 
 *       about this namespace.
 *      </p>
 *      <p>
 *       Note that local names in this namespace are intended to be
 *       defined only by the World Wide Web Consortium or its subgroups.
 *       The names currently defined in this namespace are listed below.
 *       They should not be used with conflicting semantics by any Working
 *       Group, specification, or document instance.
 *      </p>
 *      <p>   
 *       See further below in this document for more information about <a href="#usage">how to refer to this schema document from your own
 *       XSD schema documents</a> and about <a href="#nsversioning">the
 *       namespace-versioning policy governing this schema document</a>.
 *      </p>
 *     </div>
 *    </div>
 *   
 * 
 *    <div xmlns="http://www.w3.org/1999/xhtml">
 *    
 *     <h3>Father (in any context at all)</h3> 
 * 
 *     <div class="bodytext">
 *      <p>
 *       denotes Jon Bosak, the chair of 
 *       the original XML Working Group.  This name is reserved by 
 *       the following decision of the W3C XML Plenary and 
 *       XML Coordination groups:
 *      </p>
 *      <blockquote>
 *        <p>
 * 	In appreciation for his vision, leadership and
 * 	dedication the W3C XML Plenary on this 10th day of
 * 	February, 2000, reserves for Jon Bosak in perpetuity
 * 	the XML name "xml:Father".
 *        </p>
 *      </blockquote>
 *     </div>
 *    </div>
 *   
 * 
 *    <div id="usage" xml:id="usage" xmlns="http://www.w3.org/1999/xhtml">
 *     <h2>
 *       <a name="usage">About this schema document</a>
 *     </h2>
 * 
 *     <div class="bodytext">
 *      <p>
 *       This schema defines attributes and an attribute group suitable
 *       for use by schemas wishing to allow <code>xml:base</code>,
 *       <code>xml:lang</code>, <code>xml:space</code> or
 *       <code>xml:id</code> attributes on elements they define.
 *      </p>
 *      <p>
 *       To enable this, such a schema must import this schema for
 *       the XML namespace, e.g. as follows:
 *      </p>
 *      <pre>
 *           &lt;schema . . .&gt;
 *            . . .
 *            &lt;import namespace="http://www.w3.org/XML/1998/namespace"
 *                       schemaLocation="http://www.w3.org/2001/xml.xsd"/&gt;
 *      </pre>
 *      <p>
 *       or
 *      </p>
 *      <pre>
 *            &lt;import namespace="http://www.w3.org/XML/1998/namespace"
 *                       schemaLocation="http://www.w3.org/2009/01/xml.xsd"/&gt;
 *      </pre>
 *      <p>
 *       Subsequently, qualified reference to any of the attributes or the
 *       group defined below will have the desired effect, e.g.
 *      </p>
 *      <pre>
 *           &lt;type . . .&gt;
 *            . . .
 *            &lt;attributeGroup ref="xml:specialAttrs"/&gt;
 *      </pre>
 *      <p>
 *       will define a type which will schema-validate an instance element
 *       with any of those attributes.
 *      </p>
 *     </div>
 *    </div>
 *   
 * 
 *    <div id="nsversioning" xml:id="nsversioning" xmlns="http://www.w3.org/1999/xhtml">
 *     <h2>
 *       <a name="nsversioning">Versioning policy for this schema document</a>
 *     </h2>
 *     <div class="bodytext">
 *      <p>
 *       In keeping with the XML Schema WG's standard versioning
 *       policy, this schema document will persist at
 *       <a href="http://www.w3.org/2009/01/xml.xsd">
 *        http://www.w3.org/2009/01/xml.xsd</a>.
 *      </p>
 *      <p>
 *       At the date of issue it can also be found at
 *       <a href="http://www.w3.org/2001/xml.xsd">
 *        http://www.w3.org/2001/xml.xsd</a>.
 *      </p>
 *      <p>
 *       The schema document at that URI may however change in the future,
 *       in order to remain compatible with the latest version of XML
 *       Schema itself, or with the XML namespace itself.  In other words,
 *       if the XML Schema or XML namespaces change, the version of this
 *       document at <a href="http://www.w3.org/2001/xml.xsd">
 *        http://www.w3.org/2001/xml.xsd 
 *       </a> 
 *       will change accordingly; the version at 
 *       <a href="http://www.w3.org/2009/01/xml.xsd">
 *        http://www.w3.org/2009/01/xml.xsd 
 *       </a> 
 *       will not change.
 *      </p>
 *      <p>
 *       Previous dated (and unchanging) versions of this schema 
 *       document are at:
 *      </p>
 *      <ul>
 *       <li>
 *           <a href="http://www.w3.org/2009/01/xml.xsd">
 * 	http://www.w3.org/2009/01/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2007/08/xml.xsd">
 * 	http://www.w3.org/2007/08/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2004/10/xml.xsd">
 * 	http://www.w3.org/2004/10/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2001/03/xml.xsd">
 * 	http://www.w3.org/2001/03/xml.xsd</a>
 *         </li>
 *      </ul>
 *     </div>
 *    </div>
 *   
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFactory
 * @model kind="package"
 * @generated
 */
public interface FaceletTaglibPackage extends EPackage
{
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "faceletTaglib"; //$NON-NLS-1$

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http://java.sun.com/xml/ns/javaee/web-facelettaglibrary_2_0"; //$NON-NLS-1$

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "faceletTaglib"; //$NON-NLS-1$

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    FaceletTaglibPackage eINSTANCE = org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl.init();

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IdentifiableStringValueImpl <em>Identifiable String Value</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IdentifiableStringValueImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getIdentifiableStringValue()
	 * @generated
	 */
    int IDENTIFIABLE_STRING_VALUE = 23;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IdentifiableLangStringValueImpl <em>Identifiable Lang String Value</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IdentifiableLangStringValueImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getIdentifiableLangStringValue()
	 * @generated
	 */
    int IDENTIFIABLE_LANG_STRING_VALUE = 24;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DescriptionImpl <em>Description</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DescriptionImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getDescription()
	 * @generated
	 */
    int DESCRIPTION = 0;

    /**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__ANY = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DESCRIPTION__ID = 1;

    /**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DESCRIPTION__LANG = 2;

    /**
	 * The number of structural features of the '<em>Description</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DESCRIPTION_FEATURE_COUNT = 3;

				/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_STRING_VALUE__VALUE = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IDENTIFIABLE_STRING_VALUE__ID = 1;

				/**
	 * The number of structural features of the '<em>Identifiable String Value</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IDENTIFIABLE_STRING_VALUE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_LANG_STRING_VALUE__VALUE = IDENTIFIABLE_STRING_VALUE__VALUE;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IDENTIFIABLE_LANG_STRING_VALUE__ID = IDENTIFIABLE_STRING_VALUE__ID;

				/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IDENTIFIABLE_LANG_STRING_VALUE__LANG = IDENTIFIABLE_STRING_VALUE_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Identifiable Lang String Value</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IDENTIFIABLE_LANG_STRING_VALUE_FEATURE_COUNT = IDENTIFIABLE_STRING_VALUE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DisplayNameImpl <em>Display Name</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DisplayNameImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getDisplayName()
	 * @generated
	 */
    int DISPLAY_NAME = 1;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME__VALUE = IDENTIFIABLE_LANG_STRING_VALUE__VALUE;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DISPLAY_NAME__ID = IDENTIFIABLE_LANG_STRING_VALUE__ID;

    /**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DISPLAY_NAME__LANG = IDENTIFIABLE_LANG_STRING_VALUE__LANG;

    /**
	 * The number of structural features of the '<em>Display Name</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DISPLAY_NAME_FEATURE_COUNT = IDENTIFIABLE_LANG_STRING_VALUE_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DocumentRootImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getDocumentRoot()
	 * @generated
	 */
    int DOCUMENT_ROOT = 2;

    /**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__MIXED = 0;

    /**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

    /**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

    /**
	 * The feature id for the '<em><b>Facelet Taglib</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__FACELET_TAGLIB = 3;

    /**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT_FEATURE_COUNT = 4;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibCanonicalNameImpl <em>Canonical Name</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibCanonicalNameImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibCanonicalName()
	 * @generated
	 */
    int FACELET_TAGLIB_CANONICAL_NAME = 3;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_CANONICAL_NAME__VALUE = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_CANONICAL_NAME__ID = 1;

    /**
	 * The number of structural features of the '<em>Canonical Name</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_CANONICAL_NAME_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibExtensionImpl <em>Extension</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibExtensionImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibExtension()
	 * @generated
	 */
    int FACELET_TAGLIB_EXTENSION = 4;

    /**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_EXTENSION__ANY = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_EXTENSION__ID = 1;

    /**
	 * The number of structural features of the '<em>Extension</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_EXTENSION_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.UserVisibleTaglibObjectImpl <em>User Visible Taglib Object</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.UserVisibleTaglibObjectImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getUserVisibleTaglibObject()
	 * @generated
	 */
    int USER_VISIBLE_TAGLIB_OBJECT = 25;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION = 0;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME = 1;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USER_VISIBLE_TAGLIB_OBJECT__ICON = 2;

    /**
	 * The number of structural features of the '<em>User Visible Taglib Object</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT = 3;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibFunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibFunctionImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibFunction()
	 * @generated
	 */
    int FACELET_TAGLIB_FUNCTION = 5;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_FUNCTION__DESCRIPTION = USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_FUNCTION__DISPLAY_NAME = USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_FUNCTION__ICON = USER_VISIBLE_TAGLIB_OBJECT__ICON;

    /**
	 * The feature id for the '<em><b>Function Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_FUNCTION__FUNCTION_NAME = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Function Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Function Signature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_FUNCTION_FEATURE_COUNT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl <em>Tag Attribute</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagAttribute()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE = 6;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__DESCRIPTION = USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__DISPLAY_NAME = USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__ICON = USER_VISIBLE_TAGLIB_OBJECT__ICON;

    /**
	 * The feature id for the '<em><b>Name Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Required Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Type Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Method Signature Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__ID = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__NAME = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Method Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 8;

    /**
	 * The number of structural features of the '<em>Tag Attribute</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_ATTRIBUTE_FEATURE_COUNT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 9;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorExtensionImpl <em>Tag Behavior Extension</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorExtensionImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagBehaviorExtension()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION = 7;

    /**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION__ANY = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION__ID = 1;

    /**
	 * The number of structural features of the '<em>Tag Behavior Extension</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorImpl <em>Tag Behavior</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagBehavior()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR = 8;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR__DESCRIPTION = USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR__DISPLAY_NAME = USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR__ICON = USER_VISIBLE_TAGLIB_OBJECT__ICON;

    /**
	 * The feature id for the '<em><b>Behavior Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Handler Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Behavior Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Tag Behavior</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_BEHAVIOR_FEATURE_COUNT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentExtensionImpl <em>Tag Component Extension</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentExtensionImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagComponentExtension()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_COMPONENT_EXTENSION = 9;

    /**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT_EXTENSION__ANY = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT_EXTENSION__ID = 1;

    /**
	 * The number of structural features of the '<em>Tag Component Extension</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT_EXTENSION_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentImpl <em>Tag Component</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagComponent()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_COMPONENT = 10;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT__DESCRIPTION = USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT__DISPLAY_NAME = USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT__ICON = USER_VISIBLE_TAGLIB_OBJECT__ICON;

    /**
	 * The feature id for the '<em><b>Component Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Renderer Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Handler Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Component Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Tag Component</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_COMPONENT_FEATURE_COUNT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterExtensionImpl <em>Tag Converter Extension</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterExtensionImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagConverterExtension()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_CONVERTER_EXTENSION = 11;

    /**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER_EXTENSION__ANY = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER_EXTENSION__ID = 1;

    /**
	 * The number of structural features of the '<em>Tag Converter Extension</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER_EXTENSION_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterImpl <em>Tag Converter</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagConverter()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_CONVERTER = 12;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER__DESCRIPTION = USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER__DISPLAY_NAME = USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER__ICON = USER_VISIBLE_TAGLIB_OBJECT__ICON;

    /**
	 * The feature id for the '<em><b>Converter Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Handler Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Converter Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Tag Converter</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_CONVERTER_FEATURE_COUNT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagExtensionImpl <em>Tag Extension</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagExtensionImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagExtension()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_EXTENSION = 13;

    /**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_EXTENSION__ANY = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_EXTENSION__ID = 1;

    /**
	 * The number of structural features of the '<em>Tag Extension</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_EXTENSION_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTag()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG = 14;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__DESCRIPTION = USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__DISPLAY_NAME = USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__ICON = USER_VISIBLE_TAGLIB_OBJECT__ICON;

    /**
	 * The feature id for the '<em><b>Tag Name Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Handler Class Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Behavior</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__BEHAVIOR = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__COMPONENT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Converter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__CONVERTER = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Validator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__VALIDATOR = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__SOURCE = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__ATTRIBUTE = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Tag Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__TAG_EXTENSION = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Tag Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__TAG_NAME = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Handler Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG__HANDLER_CLASS = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 10;

    /**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_FEATURE_COUNT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 11;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorExtensionImpl <em>Tag Validator Extension</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorExtensionImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagValidatorExtension()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION = 15;

    /**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION__ANY = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION__ID = 1;

    /**
	 * The number of structural features of the '<em>Tag Validator Extension</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorImpl <em>Tag Validator</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagValidator()
	 * @generated
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR = 16;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR__DESCRIPTION = USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR__DISPLAY_NAME = USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR__ICON = USER_VISIBLE_TAGLIB_OBJECT__ICON;

    /**
	 * The feature id for the '<em><b>Validator Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Handler Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Validator Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Tag Validator</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_TAG_VALIDATOR_FEATURE_COUNT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl <em>Facelet Taglib</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglib()
	 * @generated
	 */
    int FACELET_TAGLIB = 17;

    /**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__DESCRIPTION = USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__DISPLAY_NAME = USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__ICON = USER_VISIBLE_TAGLIB_OBJECT__ICON;

    /**
	 * The feature id for the '<em><b>Library Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__LIBRARY_CLASS = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Namespace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__NAMESPACE = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Composite Library Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__GROUP = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Tag</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__TAG = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Function</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__FUNCTION = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Taglib Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__TAGLIB_EXTENSION = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__ID = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__VERSION = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Namespace Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB__NAMESPACE_URI = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACELET_TAGLIB__SHORT_NAME = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 10;

				/**
	 * The number of structural features of the '<em>Facelet Taglib</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACELET_TAGLIB_FEATURE_COUNT = USER_VISIBLE_TAGLIB_OBJECT_FEATURE_COUNT + 11;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FullyQualifiedClassImpl <em>Fully Qualified Class</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FullyQualifiedClassImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFullyQualifiedClass()
	 * @generated
	 */
    int FULLY_QUALIFIED_CLASS = 18;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULLY_QUALIFIED_CLASS__VALUE = IDENTIFIABLE_STRING_VALUE__VALUE;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FULLY_QUALIFIED_CLASS__ID = IDENTIFIABLE_STRING_VALUE__ID;

    /**
	 * The number of structural features of the '<em>Fully Qualified Class</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FULLY_QUALIFIED_CLASS_FEATURE_COUNT = IDENTIFIABLE_STRING_VALUE_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.GenericBooleanImpl <em>Generic Boolean</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.GenericBooleanImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getGenericBoolean()
	 * @generated
	 */
    int GENERIC_BOOLEAN = 19;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_BOOLEAN__VALUE = IDENTIFIABLE_STRING_VALUE__VALUE;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GENERIC_BOOLEAN__ID = IDENTIFIABLE_STRING_VALUE__ID;

    /**
	 * The number of structural features of the '<em>Generic Boolean</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GENERIC_BOOLEAN_FEATURE_COUNT = IDENTIFIABLE_STRING_VALUE_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IconImpl <em>Icon</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IconImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getIcon()
	 * @generated
	 */
    int ICON = 20;

    /**
	 * The feature id for the '<em><b>Small Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICON__SMALL_ICON = 0;

    /**
	 * The feature id for the '<em><b>Large Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICON__LARGE_ICON = 1;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICON__ID = 2;

    /**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICON__LANG = 3;

    /**
	 * The number of structural features of the '<em>Icon</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICON_FEATURE_COUNT = 4;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.JavaIdentifierImpl <em>Java Identifier</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.JavaIdentifierImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getJavaIdentifier()
	 * @generated
	 */
    int JAVA_IDENTIFIER = 21;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_IDENTIFIER__VALUE = IDENTIFIABLE_STRING_VALUE__VALUE;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int JAVA_IDENTIFIER__ID = IDENTIFIABLE_STRING_VALUE__ID;

    /**
	 * The number of structural features of the '<em>Java Identifier</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int JAVA_IDENTIFIER_FEATURE_COUNT = IDENTIFIABLE_STRING_VALUE_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.PathImpl <em>Path</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.PathImpl
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getPath()
	 * @generated
	 */
    int PATH = 22;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__VALUE = IDENTIFIABLE_STRING_VALUE__VALUE;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PATH__ID = IDENTIFIABLE_STRING_VALUE__ID;

    /**
	 * The number of structural features of the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PATH_FEATURE_COUNT = IDENTIFIABLE_STRING_VALUE_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion <em>Version</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibVersion()
	 * @generated
	 */
    int FACELET_TAGLIB_VERSION = 26;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBooleanBase <em>Generic Boolean Base</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBooleanBase
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getGenericBooleanBase()
	 * @generated
	 */
    int GENERIC_BOOLEAN_BASE = 27;

    /**
	 * The meta object id for the '<em>Version Type Object</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibVersionTypeObject()
	 * @generated
	 */
    int FACELET_TAGLIB_VERSION_TYPE_OBJECT = 28;

    /**
	 * The meta object id for the '<em>Fully Qualified Class Base</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFullyQualifiedClassBase()
	 * @generated
	 */
    int FULLY_QUALIFIED_CLASS_BASE = 29;

    /**
	 * The meta object id for the '<em>Java Identifier Base</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getJavaIdentifierBase()
	 * @generated
	 */
    int JAVA_IDENTIFIER_BASE = 30;

    /**
	 * The meta object id for the '<em>Path Type Base</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getPathTypeBase()
	 * @generated
	 */
    int PATH_TYPE_BASE = 31;


    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description <em>Description</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description
	 * @generated
	 */
    EClass getDescription();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description#getAny()
	 * @see #getDescription()
	 * @generated
	 */
	EAttribute getDescription_Any();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description#getId()
	 * @see #getDescription()
	 * @generated
	 */
	EAttribute getDescription_Id();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description#getLang()
	 * @see #getDescription()
	 * @generated
	 */
	EAttribute getDescription_Lang();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DisplayName
	 * @generated
	 */
    EClass getDisplayName();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot
	 * @generated
	 */
    EClass getDocumentRoot();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EAttribute getDocumentRoot_Mixed();

    /**
	 * Returns the meta object for the map '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_XMLNSPrefixMap();

    /**
	 * Returns the meta object for the map '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_XSISchemaLocation();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot#getFaceletTaglib <em>Facelet Taglib</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Facelet Taglib</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot#getFaceletTaglib()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_FaceletTaglib();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName <em>Canonical Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Canonical Name</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName
	 * @generated
	 */
    EClass getFaceletTaglibCanonicalName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName#getValue()
	 * @see #getFaceletTaglibCanonicalName()
	 * @generated
	 */
    EAttribute getFaceletTaglibCanonicalName_Value();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName#getId()
	 * @see #getFaceletTaglibCanonicalName()
	 * @generated
	 */
    EAttribute getFaceletTaglibCanonicalName_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension
	 * @generated
	 */
    EClass getFaceletTaglibExtension();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension#getAny()
	 * @see #getFaceletTaglibExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibExtension_Any();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension#getId()
	 * @see #getFaceletTaglibExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibExtension_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction
	 * @generated
	 */
    EClass getFaceletTaglibFunction();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionName <em>Function Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function Name</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionName()
	 * @see #getFaceletTaglibFunction()
	 * @generated
	 */
    EReference getFaceletTaglibFunction_FunctionName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionClass <em>Function Class</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function Class</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionClass()
	 * @see #getFaceletTaglibFunction()
	 * @generated
	 */
    EReference getFaceletTaglibFunction_FunctionClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionSignature <em>Function Signature</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function Signature</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionSignature()
	 * @see #getFaceletTaglibFunction()
	 * @generated
	 */
    EReference getFaceletTaglibFunction_FunctionSignature();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute <em>Tag Attribute</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute
	 * @generated
	 */
    EClass getFaceletTaglibTagAttribute();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getNameElement <em>Name Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name Element</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getNameElement()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EReference getFaceletTaglibTagAttribute_NameElement();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getRequiredElement <em>Required Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Required Element</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getRequiredElement()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EReference getFaceletTaglibTagAttribute_RequiredElement();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getTypeElement <em>Type Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Element</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getTypeElement()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EReference getFaceletTaglibTagAttribute_TypeElement();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getMethodSignatureElement <em>Method Signature Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Method Signature Element</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getMethodSignatureElement()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EReference getFaceletTaglibTagAttribute_MethodSignatureElement();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getId()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagAttribute_Id();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getName()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagAttribute_Name();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#isRequired()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagAttribute_Required();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getType()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagAttribute_Type();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getMethodSignature <em>Method Signature</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Signature</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute#getMethodSignature()
	 * @see #getFaceletTaglibTagAttribute()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagAttribute_MethodSignature();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension <em>Tag Behavior Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Behavior Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension
	 * @generated
	 */
    EClass getFaceletTaglibTagBehaviorExtension();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension#getAny()
	 * @see #getFaceletTaglibTagBehaviorExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagBehaviorExtension_Any();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension#getId()
	 * @see #getFaceletTaglibTagBehaviorExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagBehaviorExtension_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior <em>Tag Behavior</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Behavior</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior
	 * @generated
	 */
    EClass getFaceletTaglibTagBehavior();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getBehaviorId <em>Behavior Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getBehaviorId()
	 * @see #getFaceletTaglibTagBehavior()
	 * @generated
	 */
    EReference getFaceletTaglibTagBehavior_BehaviorId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getHandlerClass <em>Handler Class</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Handler Class</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getHandlerClass()
	 * @see #getFaceletTaglibTagBehavior()
	 * @generated
	 */
    EReference getFaceletTaglibTagBehavior_HandlerClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getBehaviorExtension <em>Behavior Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Behavior Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getBehaviorExtension()
	 * @see #getFaceletTaglibTagBehavior()
	 * @generated
	 */
    EReference getFaceletTaglibTagBehavior_BehaviorExtension();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension <em>Tag Component Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Component Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension
	 * @generated
	 */
    EClass getFaceletTaglibTagComponentExtension();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension#getAny()
	 * @see #getFaceletTaglibTagComponentExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagComponentExtension_Any();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension#getId()
	 * @see #getFaceletTaglibTagComponentExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagComponentExtension_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent <em>Tag Component</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Component</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent
	 * @generated
	 */
    EClass getFaceletTaglibTagComponent();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Type</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getComponentType()
	 * @see #getFaceletTaglibTagComponent()
	 * @generated
	 */
    EReference getFaceletTaglibTagComponent_ComponentType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getRendererType <em>Renderer Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Renderer Type</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getRendererType()
	 * @see #getFaceletTaglibTagComponent()
	 * @generated
	 */
    EReference getFaceletTaglibTagComponent_RendererType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getHandlerClass <em>Handler Class</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Handler Class</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getHandlerClass()
	 * @see #getFaceletTaglibTagComponent()
	 * @generated
	 */
    EReference getFaceletTaglibTagComponent_HandlerClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getComponentExtension <em>Component Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Component Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getComponentExtension()
	 * @see #getFaceletTaglibTagComponent()
	 * @generated
	 */
    EReference getFaceletTaglibTagComponent_ComponentExtension();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension <em>Tag Converter Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Converter Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension
	 * @generated
	 */
    EClass getFaceletTaglibTagConverterExtension();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension#getAny()
	 * @see #getFaceletTaglibTagConverterExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagConverterExtension_Any();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension#getId()
	 * @see #getFaceletTaglibTagConverterExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagConverterExtension_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter <em>Tag Converter</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Converter</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter
	 * @generated
	 */
    EClass getFaceletTaglibTagConverter();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getConverterId <em>Converter Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getConverterId()
	 * @see #getFaceletTaglibTagConverter()
	 * @generated
	 */
    EReference getFaceletTaglibTagConverter_ConverterId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getHandlerClass <em>Handler Class</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Handler Class</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getHandlerClass()
	 * @see #getFaceletTaglibTagConverter()
	 * @generated
	 */
    EReference getFaceletTaglibTagConverter_HandlerClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getConverterExtension <em>Converter Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Converter Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getConverterExtension()
	 * @see #getFaceletTaglibTagConverter()
	 * @generated
	 */
    EReference getFaceletTaglibTagConverter_ConverterExtension();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension <em>Tag Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension
	 * @generated
	 */
    EClass getFaceletTaglibTagExtension();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension#getAny()
	 * @see #getFaceletTaglibTagExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagExtension_Any();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension#getId()
	 * @see #getFaceletTaglibTagExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagExtension_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag
	 * @generated
	 */
    EClass getFaceletTaglibTag();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagNameElement <em>Tag Name Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tag Name Element</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagNameElement()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_TagNameElement();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getHandlerClassElement <em>Handler Class Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Handler Class Element</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getHandlerClassElement()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_HandlerClassElement();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getBehavior()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_Behavior();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getComponent()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_Component();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getConverter <em>Converter</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getConverter()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_Converter();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getValidator <em>Validator</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Validator</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getValidator()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_Validator();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getSource()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_Source();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getAttribute()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_Attribute();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagExtension <em>Tag Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagExtension()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EReference getFaceletTaglibTag_TagExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagName <em>Tag Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag Name</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagName()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EAttribute getFaceletTaglibTag_TagName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getHandlerClass <em>Handler Class</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Handler Class</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getHandlerClass()
	 * @see #getFaceletTaglibTag()
	 * @generated
	 */
    EAttribute getFaceletTaglibTag_HandlerClass();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension <em>Tag Validator Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Validator Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension
	 * @generated
	 */
    EClass getFaceletTaglibTagValidatorExtension();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension#getAny()
	 * @see #getFaceletTaglibTagValidatorExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagValidatorExtension_Any();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension#getId()
	 * @see #getFaceletTaglibTagValidatorExtension()
	 * @generated
	 */
    EAttribute getFaceletTaglibTagValidatorExtension_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator <em>Tag Validator</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Validator</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator
	 * @generated
	 */
    EClass getFaceletTaglibTagValidator();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator#getValidatorId <em>Validator Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Validator Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator#getValidatorId()
	 * @see #getFaceletTaglibTagValidator()
	 * @generated
	 */
    EReference getFaceletTaglibTagValidator_ValidatorId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator#getHandlerClass <em>Handler Class</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Handler Class</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator#getHandlerClass()
	 * @see #getFaceletTaglibTagValidator()
	 * @generated
	 */
    EReference getFaceletTaglibTagValidator_HandlerClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator#getValidatorExtension <em>Validator Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Validator Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator#getValidatorExtension()
	 * @see #getFaceletTaglibTagValidator()
	 * @generated
	 */
    EReference getFaceletTaglibTagValidator_ValidatorExtension();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib <em>Facelet Taglib</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Facelet Taglib</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib
	 * @generated
	 */
    EClass getFaceletTaglib();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getLibraryClass <em>Library Class</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Library Class</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getLibraryClass()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EReference getFaceletTaglib_LibraryClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Namespace</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getNamespace()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EReference getFaceletTaglib_Namespace();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getCompositeLibraryName <em>Composite Library Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Composite Library Name</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getCompositeLibraryName()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EReference getFaceletTaglib_CompositeLibraryName();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getGroup()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EAttribute getFaceletTaglib_Group();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getTag()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EReference getFaceletTaglib_Tag();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Function</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getFunction()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EReference getFaceletTaglib_Function();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getTaglibExtension <em>Taglib Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Taglib Extension</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getTaglibExtension()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EReference getFaceletTaglib_TaglibExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getId()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EAttribute getFaceletTaglib_Id();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getVersion()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EAttribute getFaceletTaglib_Version();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getNamespaceUri <em>Namespace Uri</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace Uri</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getNamespaceUri()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
    EAttribute getFaceletTaglib_NamespaceUri();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getShortName <em>Short Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short Name</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib#getShortName()
	 * @see #getFaceletTaglib()
	 * @generated
	 */
	EAttribute getFaceletTaglib_ShortName();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass <em>Fully Qualified Class</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fully Qualified Class</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass
	 * @generated
	 */
    EClass getFullyQualifiedClass();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBoolean <em>Generic Boolean</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Boolean</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBoolean
	 * @generated
	 */
    EClass getGenericBoolean();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon
	 * @generated
	 */
    EClass getIcon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getSmallIcon <em>Small Icon</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Small Icon</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getSmallIcon()
	 * @see #getIcon()
	 * @generated
	 */
    EReference getIcon_SmallIcon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getLargeIcon <em>Large Icon</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Large Icon</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getLargeIcon()
	 * @see #getIcon()
	 * @generated
	 */
    EReference getIcon_LargeIcon();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getId()
	 * @see #getIcon()
	 * @generated
	 */
    EAttribute getIcon_Id();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getLang()
	 * @see #getIcon()
	 * @generated
	 */
    EAttribute getIcon_Lang();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.JavaIdentifier <em>Java Identifier</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java Identifier</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.JavaIdentifier
	 * @generated
	 */
    EClass getJavaIdentifier();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Path <em>Path</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Path
	 * @generated
	 */
    EClass getPath();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue <em>Identifiable String Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable String Value</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue
	 * @generated
	 */
    EClass getIdentifiableStringValue();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue#getValue()
	 * @see #getIdentifiableStringValue()
	 * @generated
	 */
	EAttribute getIdentifiableStringValue_Value();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue#getId()
	 * @see #getIdentifiableStringValue()
	 * @generated
	 */
    EAttribute getIdentifiableStringValue_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue <em>Identifiable Lang String Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable Lang String Value</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue
	 * @generated
	 */
    EClass getIdentifiableLangStringValue();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue#getLang()
	 * @see #getIdentifiableLangStringValue()
	 * @generated
	 */
    EAttribute getIdentifiableLangStringValue_Lang();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject <em>User Visible Taglib Object</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Visible Taglib Object</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject
	 * @generated
	 */
    EClass getUserVisibleTaglibObject();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getDescription()
	 * @see #getUserVisibleTaglibObject()
	 * @generated
	 */
    EReference getUserVisibleTaglibObject_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getDisplayName()
	 * @see #getUserVisibleTaglibObject()
	 * @generated
	 */
    EReference getUserVisibleTaglibObject_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getIcon()
	 * @see #getUserVisibleTaglibObject()
	 * @generated
	 */
    EReference getUserVisibleTaglibObject_Icon();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Version</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion
	 * @generated
	 */
    EEnum getFaceletTaglibVersion();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBooleanBase <em>Generic Boolean Base</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Generic Boolean Base</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBooleanBase
	 * @generated
	 */
    EEnum getGenericBooleanBase();

    /**
	 * Returns the meta object for data type '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion <em>Version Type Object</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Version Type Object</em>'.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion
	 * @model instanceClass="org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion"
	 *        extendedMetaData="name='facelet-taglib-versionType:Object' baseType='facelet-taglib-versionType'"
	 * @generated
	 */
    EDataType getFaceletTaglibVersionTypeObject();

    /**
	 * Returns the meta object for data type '{@link java.lang.String <em>Fully Qualified Class Base</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Fully Qualified Class Base</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='fully-qualified-classType_._base' baseType='http://www.eclipse.org/emf/2003/XMLType#token'"
	 * @generated
	 */
    EDataType getFullyQualifiedClassBase();

    /**
	 * Returns the meta object for data type '{@link java.lang.String <em>Java Identifier Base</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Java Identifier Base</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='java-identifierType_._base' baseType='http://www.eclipse.org/emf/2003/XMLType#token' pattern='($|_|\\p{L})(\\p{L}|\\p{Nd}|_|$)*'"
	 * @generated
	 */
    EDataType getJavaIdentifierBase();

    /**
	 * Returns the meta object for data type '{@link java.lang.String <em>Path Type Base</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Path Type Base</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='pathType_._base' baseType='http://www.eclipse.org/emf/2003/XMLType#token'"
	 * @generated
	 */
    EDataType getPathTypeBase();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    FaceletTaglibFactory getFaceletTaglibFactory();

    /**
	 * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("hiding")
    interface Literals
    {
        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DescriptionImpl <em>Description</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DescriptionImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getDescription()
		 * @generated
		 */
        EClass DESCRIPTION = eINSTANCE.getDescription();

        /**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION__ANY = eINSTANCE.getDescription_Any();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION__ID = eINSTANCE.getDescription_Id();

								/**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION__LANG = eINSTANCE.getDescription_Lang();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DisplayNameImpl <em>Display Name</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DisplayNameImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getDisplayName()
		 * @generated
		 */
        EClass DISPLAY_NAME = eINSTANCE.getDisplayName();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.DocumentRootImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getDocumentRoot()
		 * @generated
		 */
        EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

        /**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

        /**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

        /**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

        /**
		 * The meta object literal for the '<em><b>Facelet Taglib</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FACELET_TAGLIB = eINSTANCE.getDocumentRoot_FaceletTaglib();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibCanonicalNameImpl <em>Canonical Name</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibCanonicalNameImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibCanonicalName()
		 * @generated
		 */
        EClass FACELET_TAGLIB_CANONICAL_NAME = eINSTANCE.getFaceletTaglibCanonicalName();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_CANONICAL_NAME__VALUE = eINSTANCE.getFaceletTaglibCanonicalName_Value();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_CANONICAL_NAME__ID = eINSTANCE.getFaceletTaglibCanonicalName_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibExtensionImpl <em>Extension</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibExtensionImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibExtension()
		 * @generated
		 */
        EClass FACELET_TAGLIB_EXTENSION = eINSTANCE.getFaceletTaglibExtension();

        /**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_EXTENSION__ANY = eINSTANCE.getFaceletTaglibExtension_Any();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_EXTENSION__ID = eINSTANCE.getFaceletTaglibExtension_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibFunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibFunctionImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibFunction()
		 * @generated
		 */
        EClass FACELET_TAGLIB_FUNCTION = eINSTANCE.getFaceletTaglibFunction();

        /**
		 * The meta object literal for the '<em><b>Function Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_FUNCTION__FUNCTION_NAME = eINSTANCE.getFaceletTaglibFunction_FunctionName();

        /**
		 * The meta object literal for the '<em><b>Function Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS = eINSTANCE.getFaceletTaglibFunction_FunctionClass();

        /**
		 * The meta object literal for the '<em><b>Function Signature</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE = eINSTANCE.getFaceletTaglibFunction_FunctionSignature();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl <em>Tag Attribute</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagAttribute()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_ATTRIBUTE = eINSTANCE.getFaceletTaglibTagAttribute();

        /**
		 * The meta object literal for the '<em><b>Name Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT = eINSTANCE.getFaceletTaglibTagAttribute_NameElement();

        /**
		 * The meta object literal for the '<em><b>Required Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT = eINSTANCE.getFaceletTaglibTagAttribute_RequiredElement();

        /**
		 * The meta object literal for the '<em><b>Type Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT = eINSTANCE.getFaceletTaglibTagAttribute_TypeElement();

        /**
		 * The meta object literal for the '<em><b>Method Signature Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT = eINSTANCE.getFaceletTaglibTagAttribute_MethodSignatureElement();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_ATTRIBUTE__ID = eINSTANCE.getFaceletTaglibTagAttribute_Id();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_ATTRIBUTE__NAME = eINSTANCE.getFaceletTaglibTagAttribute_Name();

        /**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED = eINSTANCE.getFaceletTaglibTagAttribute_Required();

        /**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE = eINSTANCE.getFaceletTaglibTagAttribute_Type();

        /**
		 * The meta object literal for the '<em><b>Method Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE = eINSTANCE.getFaceletTaglibTagAttribute_MethodSignature();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorExtensionImpl <em>Tag Behavior Extension</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorExtensionImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagBehaviorExtension()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION = eINSTANCE.getFaceletTaglibTagBehaviorExtension();

        /**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION__ANY = eINSTANCE.getFaceletTaglibTagBehaviorExtension_Any();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION__ID = eINSTANCE.getFaceletTaglibTagBehaviorExtension_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorImpl <em>Tag Behavior</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagBehavior()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_BEHAVIOR = eINSTANCE.getFaceletTaglibTagBehavior();

        /**
		 * The meta object literal for the '<em><b>Behavior Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID = eINSTANCE.getFaceletTaglibTagBehavior_BehaviorId();

        /**
		 * The meta object literal for the '<em><b>Handler Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS = eINSTANCE.getFaceletTaglibTagBehavior_HandlerClass();

        /**
		 * The meta object literal for the '<em><b>Behavior Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION = eINSTANCE.getFaceletTaglibTagBehavior_BehaviorExtension();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentExtensionImpl <em>Tag Component Extension</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentExtensionImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagComponentExtension()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_COMPONENT_EXTENSION = eINSTANCE.getFaceletTaglibTagComponentExtension();

        /**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_COMPONENT_EXTENSION__ANY = eINSTANCE.getFaceletTaglibTagComponentExtension_Any();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_COMPONENT_EXTENSION__ID = eINSTANCE.getFaceletTaglibTagComponentExtension_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentImpl <em>Tag Component</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagComponent()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_COMPONENT = eINSTANCE.getFaceletTaglibTagComponent();

        /**
		 * The meta object literal for the '<em><b>Component Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE = eINSTANCE.getFaceletTaglibTagComponent_ComponentType();

        /**
		 * The meta object literal for the '<em><b>Renderer Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE = eINSTANCE.getFaceletTaglibTagComponent_RendererType();

        /**
		 * The meta object literal for the '<em><b>Handler Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS = eINSTANCE.getFaceletTaglibTagComponent_HandlerClass();

        /**
		 * The meta object literal for the '<em><b>Component Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION = eINSTANCE.getFaceletTaglibTagComponent_ComponentExtension();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterExtensionImpl <em>Tag Converter Extension</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterExtensionImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagConverterExtension()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_CONVERTER_EXTENSION = eINSTANCE.getFaceletTaglibTagConverterExtension();

        /**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_CONVERTER_EXTENSION__ANY = eINSTANCE.getFaceletTaglibTagConverterExtension_Any();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_CONVERTER_EXTENSION__ID = eINSTANCE.getFaceletTaglibTagConverterExtension_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterImpl <em>Tag Converter</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagConverter()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_CONVERTER = eINSTANCE.getFaceletTaglibTagConverter();

        /**
		 * The meta object literal for the '<em><b>Converter Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID = eINSTANCE.getFaceletTaglibTagConverter_ConverterId();

        /**
		 * The meta object literal for the '<em><b>Handler Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS = eINSTANCE.getFaceletTaglibTagConverter_HandlerClass();

        /**
		 * The meta object literal for the '<em><b>Converter Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION = eINSTANCE.getFaceletTaglibTagConverter_ConverterExtension();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagExtensionImpl <em>Tag Extension</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagExtensionImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagExtension()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_EXTENSION = eINSTANCE.getFaceletTaglibTagExtension();

        /**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_EXTENSION__ANY = eINSTANCE.getFaceletTaglibTagExtension_Any();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_EXTENSION__ID = eINSTANCE.getFaceletTaglibTagExtension_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTag()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG = eINSTANCE.getFaceletTaglibTag();

        /**
		 * The meta object literal for the '<em><b>Tag Name Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT = eINSTANCE.getFaceletTaglibTag_TagNameElement();

        /**
		 * The meta object literal for the '<em><b>Handler Class Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT = eINSTANCE.getFaceletTaglibTag_HandlerClassElement();

        /**
		 * The meta object literal for the '<em><b>Behavior</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__BEHAVIOR = eINSTANCE.getFaceletTaglibTag_Behavior();

        /**
		 * The meta object literal for the '<em><b>Component</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__COMPONENT = eINSTANCE.getFaceletTaglibTag_Component();

        /**
		 * The meta object literal for the '<em><b>Converter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__CONVERTER = eINSTANCE.getFaceletTaglibTag_Converter();

        /**
		 * The meta object literal for the '<em><b>Validator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__VALIDATOR = eINSTANCE.getFaceletTaglibTag_Validator();

        /**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__SOURCE = eINSTANCE.getFaceletTaglibTag_Source();

        /**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__ATTRIBUTE = eINSTANCE.getFaceletTaglibTag_Attribute();

        /**
		 * The meta object literal for the '<em><b>Tag Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG__TAG_EXTENSION = eINSTANCE.getFaceletTaglibTag_TagExtension();

        /**
		 * The meta object literal for the '<em><b>Tag Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG__TAG_NAME = eINSTANCE.getFaceletTaglibTag_TagName();

        /**
		 * The meta object literal for the '<em><b>Handler Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG__HANDLER_CLASS = eINSTANCE.getFaceletTaglibTag_HandlerClass();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorExtensionImpl <em>Tag Validator Extension</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorExtensionImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagValidatorExtension()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION = eINSTANCE.getFaceletTaglibTagValidatorExtension();

        /**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION__ANY = eINSTANCE.getFaceletTaglibTagValidatorExtension_Any();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION__ID = eINSTANCE.getFaceletTaglibTagValidatorExtension_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorImpl <em>Tag Validator</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibTagValidator()
		 * @generated
		 */
        EClass FACELET_TAGLIB_TAG_VALIDATOR = eINSTANCE.getFaceletTaglibTagValidator();

        /**
		 * The meta object literal for the '<em><b>Validator Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID = eINSTANCE.getFaceletTaglibTagValidator_ValidatorId();

        /**
		 * The meta object literal for the '<em><b>Handler Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS = eINSTANCE.getFaceletTaglibTagValidator_HandlerClass();

        /**
		 * The meta object literal for the '<em><b>Validator Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION = eINSTANCE.getFaceletTaglibTagValidator_ValidatorExtension();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl <em>Facelet Taglib</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglib()
		 * @generated
		 */
        EClass FACELET_TAGLIB = eINSTANCE.getFaceletTaglib();

        /**
		 * The meta object literal for the '<em><b>Library Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB__LIBRARY_CLASS = eINSTANCE.getFaceletTaglib_LibraryClass();

        /**
		 * The meta object literal for the '<em><b>Namespace</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB__NAMESPACE = eINSTANCE.getFaceletTaglib_Namespace();

        /**
		 * The meta object literal for the '<em><b>Composite Library Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME = eINSTANCE.getFaceletTaglib_CompositeLibraryName();

        /**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB__GROUP = eINSTANCE.getFaceletTaglib_Group();

        /**
		 * The meta object literal for the '<em><b>Tag</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB__TAG = eINSTANCE.getFaceletTaglib_Tag();

        /**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB__FUNCTION = eINSTANCE.getFaceletTaglib_Function();

        /**
		 * The meta object literal for the '<em><b>Taglib Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACELET_TAGLIB__TAGLIB_EXTENSION = eINSTANCE.getFaceletTaglib_TaglibExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB__ID = eINSTANCE.getFaceletTaglib_Id();

        /**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB__VERSION = eINSTANCE.getFaceletTaglib_Version();

        /**
		 * The meta object literal for the '<em><b>Namespace Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACELET_TAGLIB__NAMESPACE_URI = eINSTANCE.getFaceletTaglib_NamespaceUri();

        /**
		 * The meta object literal for the '<em><b>Short Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FACELET_TAGLIB__SHORT_NAME = eINSTANCE.getFaceletTaglib_ShortName();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FullyQualifiedClassImpl <em>Fully Qualified Class</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FullyQualifiedClassImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFullyQualifiedClass()
		 * @generated
		 */
        EClass FULLY_QUALIFIED_CLASS = eINSTANCE.getFullyQualifiedClass();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.GenericBooleanImpl <em>Generic Boolean</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.GenericBooleanImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getGenericBoolean()
		 * @generated
		 */
        EClass GENERIC_BOOLEAN = eINSTANCE.getGenericBoolean();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IconImpl <em>Icon</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IconImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getIcon()
		 * @generated
		 */
        EClass ICON = eINSTANCE.getIcon();

        /**
		 * The meta object literal for the '<em><b>Small Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ICON__SMALL_ICON = eINSTANCE.getIcon_SmallIcon();

        /**
		 * The meta object literal for the '<em><b>Large Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ICON__LARGE_ICON = eINSTANCE.getIcon_LargeIcon();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ICON__ID = eINSTANCE.getIcon_Id();

        /**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ICON__LANG = eINSTANCE.getIcon_Lang();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.JavaIdentifierImpl <em>Java Identifier</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.JavaIdentifierImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getJavaIdentifier()
		 * @generated
		 */
        EClass JAVA_IDENTIFIER = eINSTANCE.getJavaIdentifier();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.PathImpl <em>Path</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.PathImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getPath()
		 * @generated
		 */
        EClass PATH = eINSTANCE.getPath();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IdentifiableStringValueImpl <em>Identifiable String Value</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IdentifiableStringValueImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getIdentifiableStringValue()
		 * @generated
		 */
        EClass IDENTIFIABLE_STRING_VALUE = eINSTANCE.getIdentifiableStringValue();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE_STRING_VALUE__VALUE = eINSTANCE.getIdentifiableStringValue_Value();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IDENTIFIABLE_STRING_VALUE__ID = eINSTANCE.getIdentifiableStringValue_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IdentifiableLangStringValueImpl <em>Identifiable Lang String Value</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IdentifiableLangStringValueImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getIdentifiableLangStringValue()
		 * @generated
		 */
        EClass IDENTIFIABLE_LANG_STRING_VALUE = eINSTANCE.getIdentifiableLangStringValue();

        /**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IDENTIFIABLE_LANG_STRING_VALUE__LANG = eINSTANCE.getIdentifiableLangStringValue_Lang();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.UserVisibleTaglibObjectImpl <em>User Visible Taglib Object</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.UserVisibleTaglibObjectImpl
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getUserVisibleTaglibObject()
		 * @generated
		 */
        EClass USER_VISIBLE_TAGLIB_OBJECT = eINSTANCE.getUserVisibleTaglibObject();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION = eINSTANCE.getUserVisibleTaglibObject_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME = eINSTANCE.getUserVisibleTaglibObject_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference USER_VISIBLE_TAGLIB_OBJECT__ICON = eINSTANCE.getUserVisibleTaglibObject_Icon();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion <em>Version</em>}' enum.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibVersion()
		 * @generated
		 */
        EEnum FACELET_TAGLIB_VERSION = eINSTANCE.getFaceletTaglibVersion();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBooleanBase <em>Generic Boolean Base</em>}' enum.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBooleanBase
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getGenericBooleanBase()
		 * @generated
		 */
        EEnum GENERIC_BOOLEAN_BASE = eINSTANCE.getGenericBooleanBase();

        /**
		 * The meta object literal for the '<em>Version Type Object</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFaceletTaglibVersionTypeObject()
		 * @generated
		 */
        EDataType FACELET_TAGLIB_VERSION_TYPE_OBJECT = eINSTANCE.getFaceletTaglibVersionTypeObject();

        /**
		 * The meta object literal for the '<em>Fully Qualified Class Base</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getFullyQualifiedClassBase()
		 * @generated
		 */
        EDataType FULLY_QUALIFIED_CLASS_BASE = eINSTANCE.getFullyQualifiedClassBase();

        /**
		 * The meta object literal for the '<em>Java Identifier Base</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getJavaIdentifierBase()
		 * @generated
		 */
        EDataType JAVA_IDENTIFIER_BASE = eINSTANCE.getJavaIdentifierBase();

        /**
		 * The meta object literal for the '<em>Path Type Base</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibPackageImpl#getPathTypeBase()
		 * @generated
		 */
        EDataType PATH_TYPE_BASE = eINSTANCE.getPathTypeBase();

    }

} //FaceletTaglibPackage
