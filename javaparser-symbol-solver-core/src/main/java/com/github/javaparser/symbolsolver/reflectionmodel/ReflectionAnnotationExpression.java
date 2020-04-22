package com.github.javaparser.symbolsolver.reflectionmodel;

import java.lang.annotation.Annotation;

import com.github.javaparser.resolution.annotations.ResolvedAnnotationExpression;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

/**
 * @author Marcio "DEL" Alves
 */
public class ReflectionAnnotationExpression implements ResolvedAnnotationExpression {

    private Annotation annotation;
    private TypeSolver typeSolver;

    public ReflectionAnnotationExpression(Annotation annotation, TypeSolver typeSolver) {
        this.annotation = annotation;
        this.typeSolver = typeSolver;
    }

    @Override
    public ResolvedAnnotationDeclaration getDeclaration() {
        return new ReflectionAnnotationDeclaration(annotation.annotationType(), typeSolver);
    }

}
