package com.github.javaparser.symbolsolver.javaparsermodel.declarations;

import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.resolution.annotations.ResolvedAnnotationExpression;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public class JavaParserAnnotationExpression implements ResolvedAnnotationExpression {

    private AnnotationExpr annotationExpr;
    private TypeSolver typeSolver;

    public JavaParserAnnotationExpression(AnnotationExpr annotationExpr, TypeSolver typeSolver) {
        this.annotationExpr = annotationExpr;
        this.typeSolver = typeSolver;
    }

    @Override
    public ResolvedAnnotationDeclaration getDeclaration() {
        return annotationExpr.resolve();
    }

}
