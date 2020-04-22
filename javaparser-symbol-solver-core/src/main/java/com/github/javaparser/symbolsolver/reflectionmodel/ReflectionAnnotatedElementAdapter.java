package com.github.javaparser.symbolsolver.reflectionmodel;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.resolution.annotations.ResolvedAnnotationExpression;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

/**
 * @author Marcio "DEL" Alves
 */
public class ReflectionAnnotatedElementAdapter {

    private AnnotatedElement annotatedElement;
    private TypeSolver typeSolver;

    public ReflectionAnnotatedElementAdapter(AnnotatedElement annotatedElement, TypeSolver typeSolver) {
        this.annotatedElement = annotatedElement;
        this.typeSolver = typeSolver;
    }

    public List<ResolvedAnnotationExpression> getAnnotations() {
        return Arrays.stream(annotatedElement.getDeclaredAnnotations())
                .map(ann -> new ReflectionAnnotationExpression(ann, typeSolver)).collect(Collectors.toList());
    }

}
