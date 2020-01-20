package ru.unn.agile.figuresvolumecalculator.model;

import ru.unn.agile.figuresvolumecalculator.model.constants.MathConstants;

public final class FiguresVolumeCalculator {

    public static double pyramidVolumeCalculate(final double baseSquare, final double height) {
        if (baseSquare < 0) {
            throw new IllegalArgumentException("Square can't be negative");
        }
        return MathConstants.ONE_THIRD * baseSquare * Math.abs(height);
    }

    public static double sphereVolumeCalculate(final double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius can't be negative");
        }
        return MathConstants.FOUR_THIRDS * Math.PI * Math.pow(radius, MathConstants.THREE);
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

    public static double octahedronVolumeCalculate(final double edgeLength) {
        if (edgeLength < 0) {
            throw new IllegalArgumentException("Edge can't be negative");
        }
        return MathConstants.ONE_THIRD * Math.sqrt(MathConstants.TWO)
               * Math.pow(edgeLength, MathConstants.THREE);
    }

    private FiguresVolumeCalculator() { }

    public enum TypeOfFunction {
        PYRAMID_VOLUME("Pyramid volume") {
            public String apply(final double x, final double y) {
                Double vol = FiguresVolumeCalculator.pyramidVolumeCalculate(x, y);
                return vol.toString();
            }
        },

        SPHERE_VOLUME("Sphere volume") {
            public String apply(final double x, final double y) {
                Double vol = FiguresVolumeCalculator.sphereVolumeCalculate(x);
                return vol.toString();
            }
        },

        CYLINDER_VOLUME("Cylinder volume") {
            public String apply(final double x, final double y) {
                Double vol = FiguresVolumeCalculator.cylinderVolumeCalculate(x, y);
                return vol.toString();
            }
        },

        CONE_VOLUME("Cone volume") {
            public String apply(final double x, final double y) {
                Double vol = FiguresVolumeCalculator.coneVolumeCalculate(x, y);
                return vol.toString();
            }
        },

        PARALLELEPIPED_VOLUME("Parallelepiped volume") {
            public String apply(final double x, final double y) {
                Double vol = FiguresVolumeCalculator.parallelepipedVolumeCalculate(x, y);
                return vol.toString();
            }
        },

        OCTAHEDRON_VOLUME("Octahedron volume") {
            public String apply(final double x, final double y) {
                Double vol = FiguresVolumeCalculator.octahedronVolumeCalculate(x);
                return vol.toString();
            }
        };

        private final String name;
        TypeOfFunction(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract String apply(double x, double y);

    }
}
