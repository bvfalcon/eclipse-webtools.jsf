/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.compiler.CharOperation;

/**
 * Original code is from JDT Utility methods for the Java Model.
 */
public final class JavaModelUtil {

	/**
	 * Finds a type by its qualified type name (dot separated).
	 * 
	 * @param jproject
	 *            The java project to search in
	 * @param fullyQualifiedName
	 *            The fully qualified name (type name with enclosing type names
	 *            and package (all separated by dots))
	 * @return The type found, or null if not existing
	 * @throws JavaModelException 
	 */
	public static IType findType(IJavaProject jproject,
			String fullyQualifiedName) throws JavaModelException {
		// workaround for bug 22883
		IType type = jproject.findType(fullyQualifiedName);
		if (type != null) {
			return type;
		}
		IPackageFragmentRoot[] roots = jproject.getPackageFragmentRoots();
		for (int i = 0; i < roots.length; i++) {
			IPackageFragmentRoot root = roots[i];
			type = findType(root, fullyQualifiedName);
			if (type != null && type.exists()) {
				return type;
			}
		}
		return null;
	}

	/**
	 * Returns <code>true</code> if the given package fragment root is
	 * referenced. This means it is own by a different project but is referenced
	 * by the root's parent. Returns <code>false</code> if the given root
	 * doesn't have an underlying resource.
	 * @param root 
	 * @return true if root is referenced
	 */
	public static boolean isReferenced(IPackageFragmentRoot root) {
		IResource resource = root.getResource();
		if (resource != null) {
			IProject jarProject = resource.getProject();
			IProject container = root.getJavaProject().getProject();
			return !container.equals(jarProject);
		}
		return false;
	}

	private static IType findType(IPackageFragmentRoot root,
			String fullyQualifiedName) throws JavaModelException {
		IJavaElement[] children = root.getChildren();
		for (int i = 0; i < children.length; i++) {
			IJavaElement element = children[i];
			if (element.getElementType() == IJavaElement.PACKAGE_FRAGMENT) {
				IPackageFragment pack = (IPackageFragment) element;
				if (!fullyQualifiedName.startsWith(pack.getElementName())) {
					continue;
				}
				IType type = findType(pack, fullyQualifiedName);
				if (type != null && type.exists()) {
					return type;
				}
			}
		}
		return null;
	}

	private static IType findType(IPackageFragment pack,
			String fullyQualifiedName) throws JavaModelException {
		ICompilationUnit[] cus = pack.getCompilationUnits();
		for (int i = 0; i < cus.length; i++) {
			ICompilationUnit unit = cus[i];
			IType type = findType(unit, fullyQualifiedName);
			if (type != null && type.exists()) {
				return type;
			}
		}
		return null;
	}

	private static IType findType(ICompilationUnit cu, String fullyQualifiedName)
			throws JavaModelException {
		IType[] types = cu.getAllTypes();
		for (int i = 0; i < types.length; i++) {
			IType type = types[i];
			if (getFullyQualifiedName(type).equals(fullyQualifiedName)) {
				return type;
			}
		}
		return null;
	}

	/**
	 * Finds a type by package and type name.
	 * 
	 * @param jproject
	 *            the java project to search in
	 * @param pack
	 *            The package name
	 * @param typeQualifiedName
	 *            the type qualified name (type name with enclosing type names
	 *            (separated by dots))
	 * @return the type found, or null if not existing
	 * @throws JavaModelException 
	 * @deprecated Use IJavaProject.findType(String, String) instead
	 */
	public static IType findType(IJavaProject jproject, String pack,
			String typeQualifiedName) throws JavaModelException {
		return jproject.findType(pack, typeQualifiedName);
	}

	/**
	 * Finds a type container by container name. The returned element will be of
	 * type <code>IType</code> or a <code>IPackageFragment</code>.
	 * <code>null</code> is returned if the type container could not be found.
	 * 
	 * @param jproject
	 *            The Java project defining the context to search
	 * @param typeContainerName
	 *            A dot separarted name of the type container
	 * @return the java element
	 * @throws JavaModelException 
	 * @see #getTypeContainerName(IType)
	 */
	public static IJavaElement findTypeContainer(IJavaProject jproject,
			String typeContainerName) throws JavaModelException {
		// try to find it as type
		IJavaElement result = jproject.findType(typeContainerName);
		if (result == null) {
			// find it as package
			IPath path = new Path(typeContainerName.replace('.', '/'));
			result = jproject.findElement(path);
			if (!(result instanceof IPackageFragment)) {
				result = null;
			}

		}
		return result;
	}

	/**
	 * Finds a type in a compilation unit. Typical usage is to find the
	 * corresponding type in a working copy.
	 * 
	 * @param cu
	 *            the compilation unit to search in
	 * @param typeQualifiedName
	 *            the type qualified name (type name with enclosing type names
	 *            (separated by dots))
	 * @return the type found, or null if not existing
	 * @throws JavaModelException 
	 */
	public static IType findTypeInCompilationUnit(ICompilationUnit cu,
			String typeQualifiedName) throws JavaModelException {
		IType[] types = cu.getAllTypes();
		for (int i = 0; i < types.length; i++) {
			String currName = getTypeQualifiedName(types[i]);
			if (typeQualifiedName.equals(currName)) {
				return types[i];
			}
		}
		return null;
	}

	/**
	 * Finds a a member in a compilation unit. Typical usage is to find the
	 * corresponding member in a working copy.
	 * 
	 * @param cu
	 *            the compilation unit (eg. working copy) to search in
	 * @param member
	 *            the member (eg. from the original)
	 * @return the member found, or null if not existing
	 */
	public static IMember findMemberInCompilationUnit(ICompilationUnit cu,
			IMember member) {
		IJavaElement[] elements = cu.findElements(member);
		if (elements != null && elements.length > 0) {
			return (IMember) elements[0];
		}
		return null;
	}

	/**
	 * Returns the element of the given compilation unit which is "equal" to the
	 * given element. Note that the given element usually has a parent different
	 * from the given compilation unit.
	 * 
	 * @param cu
	 *            the cu to search in
	 * @param element
	 *            the element to look for
	 * @return an element of the given cu "equal" to the given element
	 */
	public static IJavaElement findInCompilationUnit(ICompilationUnit cu,
			IJavaElement element) {
		IJavaElement[] elements = cu.findElements(element);
		if (elements != null && elements.length > 0) {
			return elements[0];
		}
		return null;
	}

	/**
	 * Returns the qualified type name of the given type using '.' as
	 * separators. This is a replace for IType.getTypeQualifiedName() which uses
	 * '$' as separators. As '$' is also a valid character in an id this is
	 * ambiguous. JavaCore PR: 1GCFUNT
	 * @param type 
	 * @return the type qualified name
	 */
	public static String getTypeQualifiedName(IType type) {
		return type.getTypeQualifiedName('.');
	}

	/**
	 * Returns the fully qualified name of the given type using '.' as
	 * separators. This is a replace for IType.getFullyQualifiedTypeName which
	 * uses '$' as separators. As '$' is also a valid character in an id this is
	 * ambiguous. JavaCore PR: 1GCFUNT
	 * @param type 
	 * @return the fully qualified name using . as the separator
	 */
	public static String getFullyQualifiedName(IType type) {
		return type.getFullyQualifiedName('.');
	}

	/**
	 * Returns the fully qualified name of a type's container. (package name or
	 * enclosing type name)
	 * @param type 
	 * @return the container name
	 */
	public static String getTypeContainerName(IType type) {
		IType outerType = type.getDeclaringType();
		if (outerType != null) {
			return outerType.getFullyQualifiedName('.');
		}
        return type.getPackageFragment().getElementName();
	}

	/**
	 * Concatenates two names. Uses a dot for separation. Both strings can be
	 * empty or <code>null</code>.
	 * @param name1 
	 * @param name2 
	 * @return name1 + name2
	 */
	public static String concatenateName(String name1, String name2) {
		StringBuffer buf = new StringBuffer();
		if (name1 != null && name1.length() > 0) {
			buf.append(name1);
		}
		if (name2 != null && name2.length() > 0) {
			if (buf.length() > 0) {
				buf.append('.');
			}
			buf.append(name2);
		}
		return buf.toString();
	}

	/**
	 * Concatenates two names. Uses a dot for separation. Both strings can be
	 * empty or <code>null</code>.
	 * @param name1 
	 * @param name2 
	 * @return name1 + name2
	 */
	public static String concatenateName(char[] name1, char[] name2) {
		StringBuffer buf = new StringBuffer();
		if (name1 != null && name1.length > 0) {
			buf.append(name1);
		}
		if (name2 != null && name2.length > 0) {
			if (buf.length() > 0) {
				buf.append('.');
			}
			buf.append(name2);
		}
		return buf.toString();
	}

	/**
	 * Evaluates if a member (possible from another package) is visible from
	 * elements in a package.
	 * 
	 * @param member
	 *            The member to test the visibility for
	 * @param pack
	 *            The package in focus
	 * @return true if visible
	 * @throws JavaModelException 
	 */
	public static boolean isVisible(IMember member, IPackageFragment pack)
			throws JavaModelException {

		int type = member.getElementType();
		if (type == IJavaElement.INITIALIZER
				|| (type == IJavaElement.METHOD && member.getElementName()
						.startsWith("<"))) { //$NON-NLS-1$
			//$NON-NLS-1$
			return false;
		}

		int otherflags = member.getFlags();
		IType declaringType = member.getDeclaringType();
		if (Flags.isPublic(otherflags)
				|| (declaringType != null && declaringType.isInterface())) {
			return true;
		} else if (Flags.isPrivate(otherflags)) {
			return false;
		}

		IPackageFragment otherpack = (IPackageFragment) findParentOfKind(
				member, IJavaElement.PACKAGE_FRAGMENT);
		return (pack != null && otherpack != null && isSamePackage(pack,
				otherpack));
	}

	/**
	 * Evaluates if a member in the focus' element hierarchy is visible from
	 * elements in a package.
	 * 
	 * @param member
	 *            The member to test the visibility for
	 * @param pack
	 *            The package of the focus element focus
	 * @return true if is visible in hiearchy
	 * @throws JavaModelException 
	 */
	public static boolean isVisibleInHierarchy(IMember member,
			IPackageFragment pack) throws JavaModelException {
		int type = member.getElementType();
		if (type == IJavaElement.INITIALIZER
				|| (type == IJavaElement.METHOD && member.getElementName()
						.startsWith("<"))) { //$NON-NLS-1$
			//$NON-NLS-1$
			return false;
		}

		int otherflags = member.getFlags();

		IType declaringType = member.getDeclaringType();
		if (Flags.isPublic(otherflags) || Flags.isProtected(otherflags)
				|| (declaringType != null && declaringType.isInterface())) {
			return true;
		} else if (Flags.isPrivate(otherflags)) {
			return false;
		}

		IPackageFragment otherpack = (IPackageFragment) findParentOfKind(
				member, IJavaElement.PACKAGE_FRAGMENT);
		return (pack != null && pack.equals(otherpack));
	}

	/**
	 * Returns the package fragment root of <code>IJavaElement</code>. If the
	 * given element is already a package fragment root, the element itself is
	 * returned.
	 * @param element 
	 * @return the package fragment root
	 */
	public static IPackageFragmentRoot getPackageFragmentRoot(
			IJavaElement element) {
		return (IPackageFragmentRoot) element
				.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT);
	}

	/**
	 * Returns the parent of the supplied java element that conforms to the
	 * given parent type or <code>null</code>, if such a parent doesn't exit.
	 * 
	 * @deprecated Use element.getParent().getAncestor(kind);
	 */
	private static IJavaElement findParentOfKind(IJavaElement element, int kind) {
		if (element != null && element.getParent() != null) {
			return element.getParent().getAncestor(kind);
		}
		return null;
	}

	/**
	 * Finds a method in a type. This searches for a method with the same name
	 * and signature. Parameter types are only compared by the simple name, no
	 * resolving for the fully qualified type name is done. Constructors are
	 * only compared by parameters, not the name.
	 * 
	 * @param name
	 *            The name of the method to find
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param isConstructor
	 *            If the method is a constructor
	 * @param type 
	 * @return The first found method or <code>null</code>, if nothing found
	 * @throws JavaModelException 
	 */
	public static IMethod findMethod(String name, String[] paramTypes,
			boolean isConstructor, IType type) throws JavaModelException {
		return findMethod(name, paramTypes, isConstructor, type.getMethods());
	}

	/**
	 * Finds a method by name. This searches for a method with a name and
	 * signature. Parameter types are only compared by the simple name, no
	 * resolving for the fully qualified type name is done. Constructors are
	 * only compared by parameters, not the name.
	 * 
	 * @param name
	 *            The name of the method to find
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param isConstructor
	 *            If the method is a constructor
	 * @param methods
	 *            The methods to search in
	 * @return The found method or <code>null</code>, if nothing found
	 * @throws JavaModelException 
	 */
	public static IMethod findMethod(String name, String[] paramTypes,
			boolean isConstructor, IMethod[] methods) throws JavaModelException {
		for (int i = methods.length - 1; i >= 0; i--) {
			if (isSameMethodSignature(name, paramTypes, isConstructor,
					methods[i])) {
				return methods[i];
			}
		}
		return null;
	}

	/**
	 * Finds a method declararion in a type's hierarchy. The search is top down,
	 * so this returns the first declaration of the method in the hierarchy.
	 * This searches for a method with a name and signature. Parameter types are
	 * only compared by the simple name, no resolving for the fully qualified
	 * type name is done. Constructors are only compared by parameters, not the
	 * name.
	 * @param hierarchy 
	 * 
	 * @param type
	 *            Searches in this type's supertypes.
	 * @param name
	 *            The name of the method to find
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param isConstructor
	 *            If the method is a constructor
	 * @return The first method found or null, if nothing found
	 * @throws JavaModelException 
	 */
	public static IMethod findMethodDeclarationInHierarchy(
			ITypeHierarchy hierarchy, IType type, String name,
			String[] paramTypes, boolean isConstructor)
			throws JavaModelException {
		IType[] superTypes = hierarchy.getAllSupertypes(type);
		for (int i = superTypes.length - 1; i >= 0; i--) {
			IMethod first = findMethod(name, paramTypes, isConstructor,
					superTypes[i]);
			if (first != null && !Flags.isPrivate(first.getFlags())) {
				// the order getAllSupertypes does make assumptions of the order
				// of inner elements -> search recursivly
				IMethod res = findMethodDeclarationInHierarchy(hierarchy, first
						.getDeclaringType(), name, paramTypes, isConstructor);
				if (res != null) {
					return res;
				}
				return first;
			}
		}
		return null;
	}

	/**
	 * Finds a method implementation in a type's classhierarchy. The search is
	 * bottom-up, so this returns the nearest overridden method. Does not find
	 * methods in interfaces or abstract methods. This searches for a method
	 * with a name and signature. Parameter types are only compared by the
	 * simple name, no resolving for the fully qualified type name is done.
	 * Constructors are only compared by parameters, not the name.
	 * @param hierarchy 
	 * 
	 * @param type
	 *            Type to search the superclasses
	 * @param name
	 *            The name of the method to find
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param isConstructor
	 *            If the method is a constructor
	 * @return The first method found or null, if nothing found
	 * @throws JavaModelException 
	 */
	public static IMethod findMethodImplementationInHierarchy(
			ITypeHierarchy hierarchy, IType type, String name,
			String[] paramTypes, boolean isConstructor)
			throws JavaModelException {
		IType[] superTypes = hierarchy.getAllSuperclasses(type);
		for (int i = 0; i < superTypes.length; i++) {
			IMethod found = findMethod(name, paramTypes, isConstructor,
					superTypes[i]);
			if (found != null) {
				if (Flags.isAbstract(found.getFlags())) {
					return null;
				}
				return found;
			}
		}
		return null;
	}

	private static IMethod findMethodInHierarchy(ITypeHierarchy hierarchy,
			IType type, String name, String[] paramTypes, boolean isConstructor)
			throws JavaModelException {
		IMethod method = findMethod(name, paramTypes, isConstructor, type);
		if (method != null) {
			return method;
		}
		IType superClass = hierarchy.getSuperclass(type);
		if (superClass != null) {
			IMethod res = findMethodInHierarchy(hierarchy, superClass, name,
					paramTypes, isConstructor);
			if (res != null) {
				return res;
			}
		}
		if (!isConstructor) {
			IType[] superInterfaces = hierarchy.getSuperInterfaces(type);
			for (int i = 0; i < superInterfaces.length; i++) {
				IMethod res = findMethodInHierarchy(hierarchy,
						superInterfaces[i], name, paramTypes, false);
				if (res != null) {
					return res;
				}
			}
		}
		return method;
	}

	/**
	 * Finds the method that is defines/declares the given method. The search is
	 * bottom-up, so this returns the nearest defining/declaring method.
	 * @param typeHierarchy 
	 * @param type 
	 * @param methodName 
	 * @param paramTypes 
	 * @param isConstructor 
	 * 
	 * @param testVisibility
	 *            If true the result is tested on visibility. Null is returned
	 *            if the method is not visible.
	 * @return the method or null
	 * @throws JavaModelException
	 */
	public static IMethod findMethodDefininition(ITypeHierarchy typeHierarchy,
			IType type, String methodName, String[] paramTypes,
			boolean isConstructor, boolean testVisibility)
			throws JavaModelException {
		IType superClass = typeHierarchy.getSuperclass(type);
		if (superClass != null) {
			IMethod res = findMethodInHierarchy(typeHierarchy, superClass,
					methodName, paramTypes, isConstructor);
			if (res != null && !Flags.isPrivate(res.getFlags())) {
				if (!testVisibility
						|| isVisibleInHierarchy(res, type.getPackageFragment())) {
					return res;
				}
			}
		}
		if (!isConstructor) {
			IType[] interfaces = typeHierarchy.getSuperInterfaces(type);
			for (int i = 0; i < interfaces.length; i++) {
				IMethod res = findMethodInHierarchy(typeHierarchy,
						interfaces[i], methodName, paramTypes, false);
				if (res != null) {
					return res; // methods from interfaces are always public and
					// therefore visible
				}
			}
		}
		return null;
	}

	/**
	 * Tests if a method equals to the given signature. Parameter types are only
	 * compared by the simple name, no resolving for the fully qualified type
	 * name is done. Constructors are only compared by parameters, not the name.
	 * 
	 * @param name
	 *            Name of the method
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param isConstructor
	 *            Specifies if the method is a constructor
	 * @param curr 
	 * @return Returns <code>true</code> if the method has the given name and
	 *         parameter types and constructor state.
	 * @throws JavaModelException 
	 */
	public static boolean isSameMethodSignature(String name,
			String[] paramTypes, boolean isConstructor, IMethod curr)
			throws JavaModelException {
		if (isConstructor || name.equals(curr.getElementName())) {
			if (isConstructor == curr.isConstructor()) {
				String[] currParamTypes = curr.getParameterTypes();
				if (paramTypes.length == currParamTypes.length) {
					for (int i = 0; i < paramTypes.length; i++) {
						String t1 = Signature.getSimpleName(Signature
								.toString(paramTypes[i]));
						String t2 = Signature.getSimpleName(Signature
								.toString(currParamTypes[i]));
						if (!t1.equals(t2)) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Tests if two <code>IPackageFragment</code>s represent the same logical
	 * java package.
	 * @param pack1 
	 * @param pack2 
	 * 
	 * @return <code>true</code> if the package fragments' names are equal.
	 */
	public static boolean isSamePackage(IPackageFragment pack1,
			IPackageFragment pack2) {
		return pack1.getElementName().equals(pack2.getElementName());
	}

	/**
	 * Checks whether the given type has a valid main method or not.
	 * @param type 
	 * @return true if type has a main method
	 * @throws JavaModelException 
	 */
	public static boolean hasMainMethod(IType type) throws JavaModelException {
		IMethod[] methods = type.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isMainMethod()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the field is boolean.
	 * @param field 
	 * @return true if the file is of primitive boolean type
	 * @throws JavaModelException 
	 */
	public static boolean isBoolean(IField field) throws JavaModelException {
		return field.getTypeSignature().equals(Signature.SIG_BOOLEAN);
	}

	/**
	 * Tests if the given element is on the class path of its containing
	 * project. Handles the case that the containing project isn't a Java
	 * project.
	 * @param element 
	 * @return true if element in on the class path?
	 */
	public static boolean isOnClasspath(IJavaElement element) {
		IJavaProject project = element.getJavaProject();
		if (!project.exists())
			return false;
		return project.isOnClasspath(element);
	}

	/**
	 * Resolves a type name in the context of the declaring type.
	 * 
	 * @param refTypeSig
	 *            the type name in signature notation (for example 'QVector')
	 *            this can also be an array type, but dimensions will be
	 *            ignored.
	 * @param declaringType
	 *            the context for resolving (type where the reference was made
	 *            in)
	 * @return returns the fully qualified type name or build-in-type name. if a
	 *         unresoved type couldn't be resolved null is returned
	 * @throws JavaModelException 
	 */
	public static String getResolvedTypeName(String refTypeSig,
			IType declaringType) throws JavaModelException {
		int arrayCount = Signature.getArrayCount(refTypeSig);
		char type = refTypeSig.charAt(arrayCount);
		if (type == Signature.C_UNRESOLVED) {
			int semi = refTypeSig
					.indexOf(Signature.C_SEMICOLON, arrayCount + 1);
			if (semi == -1) {
				throw new IllegalArgumentException();
			}
			String name = refTypeSig.substring(arrayCount + 1, semi);

			String[][] resolvedNames = declaringType.resolveType(name);
			if (resolvedNames != null && resolvedNames.length > 0) {
				return JavaModelUtil.concatenateName(resolvedNames[0][0],
						resolvedNames[0][1]);
			}
			return null;
		}
        return Signature.toString(refTypeSig.substring(arrayCount));
	}

	/**
	 * Returns if a CU can be edited.
	 * @param cu 
	 * @return true if cu is editable
	 */
	public static boolean isEditable(ICompilationUnit cu) {
		IResource resource = toOriginal(cu).getResource();
		return (resource.exists() && !resource.getResourceAttributes()
				.isReadOnly());
	}

	/**
	 * Finds a qualified import for a type name.
	 * @param cu 
	 * @param simpleName 
	 * @return the import declaration or null
	 * @throws JavaModelException 
	 */
	public static IImportDeclaration findImport(ICompilationUnit cu,
			String simpleName) throws JavaModelException {
		IImportDeclaration[] existing = cu.getImports();
		for (int i = 0; i < existing.length; i++) {
			String curr = existing[i].getElementName();
			if (curr.endsWith(simpleName)) {
				int dotPos = curr.length() - simpleName.length() - 1;
				if ((dotPos == -1)
						|| (dotPos > 0 && curr.charAt(dotPos) == '.')) {
					return existing[i];
				}
			}
		}
		return null;
	}

	/**
	 * Returns the original if the given member. If the member is already an
	 * original the input is returned. The returned member might not exist
	 * @param member 
	 * @return the original IMember
	 */
	public static IMember toOriginal(IMember member) {
		if (member instanceof IMethod) {
			return toOriginalMethod((IMethod) member);
		}

		return (IMember) member.getPrimaryElement();
		/*
		 * ICompilationUnit cu= member.getCompilationUnit(); if (cu != null &&
		 * cu.isWorkingCopy()) return (IMember)cu.getOriginal(member); return
		 * member;
		 */
	}

	/*
	 * XXX workaround for bug 18568
	 * http://bugs.eclipse.org/bugs/show_bug.cgi?id=18568 to be removed once the
	 * bug is fixed
	 */
	private static IMethod toOriginalMethod(IMethod method) {
		ICompilationUnit cu = method.getCompilationUnit();
		if (cu == null || isPrimary(cu)) {
			return method;
		}
		try {
			// use the workaround only if needed
			if (!method.getElementName().equals(
					method.getDeclaringType().getElementName()))
				return (IMethod) method.getPrimaryElement();

			IType originalType = (IType) toOriginal(method.getDeclaringType());
			IMethod[] methods = originalType.findMethods(method);
			boolean isConstructor = method.isConstructor();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].isConstructor() == isConstructor)
					return methods[i];
			}
			return null;
		} catch (JavaModelException e) {
			return null;
		}
	}

	// private static boolean PRIMARY_ONLY = false;

	/**
	 * Returns the original cu if the given cu is a working copy. If the cu is
	 * already an original the input cu is returned. The returned cu might not
	 * exist
	 * @param cu 
	 * @return the original compiliation unit
	 */
	public static ICompilationUnit toOriginal(ICompilationUnit cu) {
		// To stay compatible with old version returned null
		// if cu is null
		if (cu == null)
			return cu;
		return cu.getPrimary();
	}

	/**
	 * Returns the original element if the given element is a working copy. If
	 * the cu is already an original the input element is returned. The returned
	 * element might not exist
	 * @param element 
	 * @return element's primary element
	 */
	public static IJavaElement toOriginal(IJavaElement element) {
		return element.getPrimaryElement();
	}

	/**
	 * Returns true if a cu is a primary cu (original or shared working copy)
	 * @param cu 
	 * @return true if cu  is primary
	 */
	public static boolean isPrimary(ICompilationUnit cu) {
		return cu.getOwner() == null;
	}

	/**
	 * http://bugs.eclipse.org/bugs/show_bug.cgi?id=19253
	 * 
	 * Reconciling happens in a separate thread. This can cause a situation
	 * where the Java element gets disposed after an exists test has been done.
	 * So we should not log not present exceptions when they happen in working
	 * copies.
	 * @param exception 
	 * @return true if filter not present
	 */
	public static boolean filterNotPresentException(CoreException exception) {
		if (!(exception instanceof JavaModelException)) {
			return true;
		}
		JavaModelException je = (JavaModelException) exception;
		if (!je.isDoesNotExist()) {
			return true;
		}
		IJavaElement[] elements = je.getJavaModelStatus().getElements();
		for (int i = 0; i < elements.length; i++) {
			IJavaElement element = elements[i];
			ICompilationUnit unit = (ICompilationUnit) element
					.getAncestor(IJavaElement.COMPILATION_UNIT);
			if (unit == null) {
				return true;
			}
			if (!unit.isWorkingCopy()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param type
	 * @param pm
	 * @return all supertypes of type
	 * @throws JavaModelException
	 */
	public static IType[] getAllSuperTypes(IType type, IProgressMonitor pm)
			throws JavaModelException {
		// workaround for 23656
		Set types = new HashSet(Arrays.asList(type.newSupertypeHierarchy(pm)
				.getAllSupertypes(type)));
		IType objekt = type.getJavaProject().findType("java.lang.Object");//$NON-NLS-1$
		if (objekt != null) {
			types.add(objekt);
		}
		return (IType[]) types.toArray(new IType[types.size()]);
	}

	/**
	 * @param resourcePath
	 * @param exclusionPatterns
	 * @return true if resourcePath is excluded by exclusion patterns
	 */
	public static boolean isExcludedPath(IPath resourcePath,
			IPath[] exclusionPatterns) {
		char[] path = resourcePath.toString().toCharArray();
		for (int i = 0, length = exclusionPatterns.length; i < length; i++) {
			char[] pattern = exclusionPatterns[i].toString().toCharArray();
			if (CharOperation.pathMatch(pattern, path, true, '/')) {
				return true;
			}
		}
		return false;
	}

	/*

	 * @see IClasspathEntry#getExclusionPatterns
	 */
	/**
	 * Returns whether the given resource path matches one of the exclusion
	 * patterns.
	 * 
	 * @param resourcePath
	 * @param exclusionPatterns
	 * @return true if resourcePath is excluded
	 */
	public static boolean isExcluded(IPath resourcePath,
			char[][] exclusionPatterns) {
		if (exclusionPatterns == null) {
			return false;
		}
		char[] path = resourcePath.toString().toCharArray();
		for (int i = 0, length = exclusionPatterns.length; i < length; i++) {
			if (CharOperation.pathMatch(exclusionPatterns[i], path, true, '/')) {
				return true;
			}
		}
		return false;
	}

	private static Boolean fgIsJDTCore_1_5 = null;

	/**
	 * @return true if JRE 1.5 in enabled.
	 */
	public static boolean isJDTCore_1_5() {
		if (fgIsJDTCore_1_5 == null) {
			fgIsJDTCore_1_5 = JavaCore
					.getDefaultOptions()
					.containsKey(
							"org.eclipse.jdt.core.compiler.problem.unsafeTypeOperation") ? Boolean.TRUE //$NON-NLS-1$
					: Boolean.FALSE;
		}
		return fgIsJDTCore_1_5.booleanValue();
	}

	/**
	 * Helper method that tests if an classpath entry can be found in a
	 * container. <code>null</code> is returned if the entry can not be found
	 * or if the container does not allows the configuration of source
	 * attachments
	 * 
	 * @param jproject
	 *            The container's parent project
	 * @param containerPath
	 *            The path of the container
	 * @param libPath
	 *            The path of the library to be found
	 * @return IClasspathEntry A classpath entry from the container of
	 *         <code>null</code> if the container can not be modified.
	 * @throws JavaModelException 
	 */
	public static IClasspathEntry getClasspathEntryToEdit(
			IJavaProject jproject, IPath containerPath, IPath libPath)
			throws JavaModelException {
		IClasspathContainer container = JavaCore.getClasspathContainer(
				containerPath, jproject);
		ClasspathContainerInitializer initializer = JavaCore
				.getClasspathContainerInitializer(containerPath.segment(0));
		if (container != null
				&& initializer != null
				&& initializer.canUpdateClasspathContainer(containerPath,
						jproject)) {
			IClasspathEntry[] entries = container.getClasspathEntries();
			for (int i = 0; i < entries.length; i++) {
				IClasspathEntry curr = entries[i];
				IClasspathEntry resolved = JavaCore
						.getResolvedClasspathEntry(curr);
				if (resolved != null && libPath.equals(resolved.getPath())) {
					return curr; // return the real entry
				}
			}
		}
		return null; // attachment not possible
	}
}
