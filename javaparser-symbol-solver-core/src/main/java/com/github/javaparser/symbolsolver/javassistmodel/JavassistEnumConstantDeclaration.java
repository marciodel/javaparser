/*
 * Copyright (C) 2015-2016 Federico Tomassetti
 * Copyright (C) 2017-2020 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser.symbolsolver.javassistmodel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.javaparser.resolution.declarations.ResolvedEnumConstantDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.model.typesystem.ReferenceTypeImpl;
import com.github.javaparser.resolution.annotations.ResolvedAnnotationExpression;

import javassist.CtField;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.FieldInfo;

/**
 * @author Federico Tomassetti
 */
public class JavassistEnumConstantDeclaration implements ResolvedEnumConstantDeclaration {

    private CtField ctField;
    private TypeSolver typeSolver;
    private ResolvedType type;

    public JavassistEnumConstantDeclaration(CtField ctField, TypeSolver typeSolver) {
        if (ctField == null) {
            throw new IllegalArgumentException();
        }
        if ((ctField.getFieldInfo2().getAccessFlags() & AccessFlag.ENUM) == 0) {
            throw new IllegalArgumentException(
                    "Trying to instantiate a JavassistEnumConstantDeclaration with something which is not an enum field: "
                            + ctField.toString());
        }
        this.ctField = ctField;
        this.typeSolver = typeSolver;
    }


    @Override
    public String getName() {
        return ctField.getName();
    }

    @Override
    public ResolvedType getType() {
        if (type == null) {
            type = new ReferenceTypeImpl(new JavassistEnumDeclaration(ctField.getDeclaringClass(), typeSolver),
                    typeSolver);
        }
        return type;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "ctField=" + ctField.getName() +
                ", typeSolver=" + typeSolver +
                '}';
    }

    @Override
    public List<ResolvedAnnotationExpression> getAnnotations() {
        FieldInfo fi = ctField.getFieldInfo2();
        AnnotationsAttribute ainfo = (AnnotationsAttribute)
                    fi.getAttribute(AnnotationsAttribute.invisibleTag);  
        AnnotationsAttribute ainfo2 = (AnnotationsAttribute)
                    fi.getAttribute(AnnotationsAttribute.visibleTag);  

        return Stream.concat(Arrays.stream(ainfo.getAnnotations()), Arrays.stream(ainfo2.getAnnotations()))
                    .map(ann -> new JavassistAnnotationExpression(ann, typeSolver))
                    .collect(Collectors.toList());
      }

}
