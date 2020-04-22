package com.github.javaparser.symbolsolver.javassistmodel;

import com.github.javaparser.resolution.annotations.ResolvedAnnotationExpression;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import javassist.bytecode.annotation.Annotation;

/**
 * @author Marcio "DEL" Alves
 */
public class JavassistAnnotationExpression implements ResolvedAnnotationExpression {

    private Annotation annotation;
    private TypeSolver typeSolver;

    public JavassistAnnotationExpression(Annotation annotation, TypeSolver typeSolver) {
        this.annotation = annotation;
        this.typeSolver = typeSolver;
	}

	@Override
    public ResolvedAnnotationDeclaration getDeclaration() {
        return (ResolvedAnnotationDeclaration) typeSolver.solveType(annotation.getTypeName());
    }

}