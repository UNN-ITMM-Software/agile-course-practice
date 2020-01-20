package ru.unn.agile.figuresvolumecalculator;

import org.junit.Test;
import ru.unn.agile.figuresvolumecalculator.model.FiguresVolumeCalculator;

import static org.junit.Assert.*;

public class FiguresVolumeCalculatorTest {

    private final double delta = 1e-2;

    @Test
    public void canCalculatePyramidVolume() {
        final double expectedVolume = 3;
        final double baseSquare = 3;
        final double height = 3;
        final double actualVolume =
                FiguresVolumeCalculator.pyramidVolumeCalculate(baseSquare, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canThrowExceptionIfSquareIsNegative() {
        final double baseSquare = -3;
        final double height = 3;
        FiguresVolumeCalculator.pyramidVolumeCalculate(baseSquare, height);
    }

    @Test
    public void canCalculatePyramidVolumeWithNegativeHeight() {
        final double expectedVolume = 3;
        final double baseSquare = 3;
        final double height = -3;
        final double actualVolume =
                FiguresVolumeCalculator.pyramidVolumeCalculate(baseSquare, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test
    public void canCalculateSphereVolume() {
        final double expectedVolume = 113.097;
        final double radius = 3;
        final double actualVolume = FiguresVolumeCalculator.sphereVolumeCalculate(radius);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canThrowExceptionIfSphereRadiusIsNegative() {
        final double radius = -3;
        FiguresVolumeCalculator.sphereVolumeCalculate(radius);
    }

    @Test
    public void canCalculateCylinderVolume() {
        final double expectedVolume = 84.82;
        final double baseRadius = 3;
        final double height = 3;
        final double actualVolume =
                FiguresVolumeCalculator.cylinderVolumeCalculate(baseRadius, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test
    public void canCalculateCylinderVolumeWithNegativeHeight() {
        final double expectedVolume = 84.82;
        final double baseRadius = 3;
        final double height = -3;
        final double actualVolume =
                FiguresVolumeCalculator.cylinderVolumeCalculate(baseRadius, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canThrowExceptionIfCylinderBaseRadiusIsNegative() {
        final double baseRadius = -3;
        final double height = 3;
        FiguresVolumeCalculator.cylinderVolumeCalculate(baseRadius, height);
    }

    @Test
    public void canCalculateConeVolumeWithZeroHeight() {
        final double expectedVolume = 0;
        final double baseRadius = 1;
        final double height = 0;
        final double actualVolume =
                FiguresVolumeCalculator.coneVolumeCalculate(baseRadius, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test
    public void canCalculateConeVolumeWithArbitraryPositiveParams() {
        final double expectedVolume = 28.27;
        final double baseRadius = 3;
        final double height = 3;
        final double actualVolume =
                FiguresVolumeCalculator.coneVolumeCalculate(baseRadius, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test
    public void canCalculateConeVolumeWithNegativeHeight() {
        final double expectedVolume = 28.27;
        final double baseRadius = 3;
        final double height = -3;
        final double actualVolume =
                FiguresVolumeCalculator.coneVolumeCalculate(baseRadius, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canThrowExceptionIfConeBaseRadiusIsNegative() {
        final double baseRadius = -3;
        final double height = 3;
        FiguresVolumeCalculator.coneVolumeCalculate(baseRadius, height);
    }

    @Test
    public void canCalculateParallelepipedVolumeWithZeroHeight() {
        final double expectedVolume = 0;
        final double baseSquare = 1;
        final double height = 0;
        final double actualVolume =
                FiguresVolumeCalculator.parallelepipedVolumeCalculate(baseSquare, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test
    public void canCalculateParallelepipedVolumeWithArbitraryPositiveParams() {
        final double expectedVolume = 9;
        final double baseSquare = 3;
        final double height = 3;
        final double actualVolume =
                FiguresVolumeCalculator.parallelepipedVolumeCalculate(baseSquare, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test
    public void canCalculateParallelepipedVolumeWithNegativeHeight() {
        final double expectedVolume = 9;
        final double baseSquare = 3;
        final double height = -3;
        final double actualVolume =
                FiguresVolumeCalculator.parallelepipedVolumeCalculate(baseSquare, height);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canThrowExceptionIfParallelepipedBaseSquareIsNegative() {
        final double baseSquare = -3;
        final double height = 3;
        FiguresVolumeCalculator.parallelepipedVolumeCalculate(baseSquare, height);
    }

    @Test
    public void canCalculateOctahedronVolume() {
        final double expectedVolume = 12.72;
        final double edgeLength = 3;
        final double actualVolume = FiguresVolumeCalculator.octahedronVolumeCalculate(edgeLength);
        assertEquals(expectedVolume, actualVolume, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canThrowExceptionIfOctahedronEdgeIsNegative() {
        final double edgeLength = -1;
        FiguresVolumeCalculator.octahedronVolumeCalculate(edgeLength);
    }
}
