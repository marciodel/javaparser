package com.github.javaparser.resolution.annotations;

import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;

/**
 * @author Marcio "DEL" Alves
 */
public interface ResolvedAnnotationExpression {
    ResolvedAnnotationDeclaration getDeclaration();
}
