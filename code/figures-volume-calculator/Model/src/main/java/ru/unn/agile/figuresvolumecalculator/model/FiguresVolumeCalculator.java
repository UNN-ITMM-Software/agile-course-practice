package ru.unn.agile.figuresvolumecalculator.model;

import ru.unn.agile.figuresvolumecalculator.model.constants.MathConstants;

public final class FiguresVolumeCalculator {

    public static double pyramidVolumeCalculate(final double baseSquare, final double height) {
        if (baseSquare < 0) {
            throw new IllegalArgumentException("Square can't be negative");
        }
        return MathConstants.ONE_THIRD * baseSquare * Math.abs(height);
    }

    public static double sphereVolumeCalculate(final double radius, final double secondParameter) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius can't be negative");
        }
        return MathConstants.FOUR_THIRDS * Math.PI * Math.pow(radius, MathConstants.THREE)
                * secondParameter;
    }

    public static double cylinderVolumeCalculate(final double baseRadius, final double height) {
        if (baseRadius < 0) {
            throw new IllegalArgumentException("Radius can't be negative");
        }
        return Math.PI * Math.pow(baseRadius, MathConstants.TWO) * Math.abs(height);
    }

    public static double coneVolumeCalculate(final double baseRadius, final double height) {
        if (baseRadius < 0) {
            throw new IllegalArgumentException("Radius can't be negative");
        }
        return MathConstants.ONE_THIRD * Math.PI
               * Math.pow(baseRadius, MathConstants.TWO) * Math.abs(height);
    }

    public static double parallelepipedVolumeCalculate(final double baseSquare,
                                                       final double height) {
        if (baseSquare < 0) {
            throw new IllegalArgumentException("Square can't be negative");
        }
        return baseSquare * Math.abs(height);
    }

    public static double octahedronVolumeCalculate(final double edgeLength,
                                                   final double secondParameter) {
        if (edgeLength < 0) {
            throw new IllegalArgumentException("Edge can't be negative");
        }
        return MathConstants.ONE_THIRD * Math.sqrt(MathConstants.TWO)
               * Math.pow(edgeLength, MathConstants.THREE * secondParameter);
    }

    public enum Operation {
        CALCULATE_PYRAMID_VOLUME("Calculate Pyramid Volume") {
            public double apply(final double baseSquare, final double height) {
                return FiguresVolumeCalculator.pyramidVolumeCalculate(baseSquare, height);
            }
        },
        CALCULATE_SPHERE_VOLUME("Calculate Sphere Volume") {
            public double apply(final double radius, final double secondParameter) {
                return FiguresVolumeCalculator.sphereVolumeCalculate(radius, secondParameter);
            }
        },

        CALCULATE_CYLINDER_VOLUME("Calculate Cylinder Volume") {
            public double apply(final double baseRadius, final double height) {
                return FiguresVolumeCalculator.cylinderVolumeCalculate(baseRadius, height);
            }
        },

        CALCULATE_CONE_VOLUME("Calculate Cone Volume") {
            public double apply(final double baseRadius, final double height) {
                return FiguresVolumeCalculator.coneVolumeCalculate(baseRadius, height);
            }
        },

        CALCULATE_PARALLELEPIPED_VOLUME("Calculate Parallelepiped Volume") {
            public double apply(final double baseSquare, final double height) {
                return FiguresVolumeCalculator.parallelepipedVolumeCalculate(baseSquare, height);
            }
        },
        CALCULATE_OCTAHEDRON_VOLUME("Calculate Octahedron Volume") {
            public double apply(final double edgeLength, final double secondParameter) {
                return FiguresVolumeCalculator.octahedronVolumeCalculate(edgeLength,
                        secondParameter);
            }
        };


        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
        public abstract double apply(double firstParameters, double secondParameters);
    }

    private FiguresVolumeCalculator() { }
}
