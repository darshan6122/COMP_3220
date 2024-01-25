import java.util.*;

/**
 * Generic shape represented by an abstract base class.
 */
abstract class CShape {
    private static int counter = 0;
    protected int id;

    /**
     * Creates a shape and gives it a unique number.
     */
    public CShape() {
        this.id = ++counter;
    }

    /**
     * Obtains the shape's type.
     *
     * @return a string that signifies the kind of shape.
     */
    public abstract String getType();

    /**
     * Get the shape's dimensions.
     *
     * @return a string that represents the shape's dimensions.
     */
    public abstract String getDimensions();

    /**
     * obtains the shape's unique identifier.
     *
     * @return the shape's unique identity.
     */
    public int getId() {
        return id;
    }
}

/**
 * represents one particular kind of CShape, which is an oval form.
 */
class COval extends CShape {
    private int horizontalRadius;
    private int verticalRadius;

    /**
     * creates an oval form with defined vertical and horizontal radii.
     *
     * @param horizontalRadius The horizontal radius of the oval.
     * @param verticalRadius   The vertical radius of the oval.
     */
    public COval(int horizontalRadius, int verticalRadius) {
        super();
        this.horizontalRadius = Math.max(1, Math.min(horizontalRadius, 100));
        this.verticalRadius = Math.max(1, Math.min(verticalRadius, 100));
    }

    /**
     * obtains the shape's type, which for COval is "OVAL."
     *
     * @return The string "OVAL".
     */
    @Override
    public String getType() {
        return "OVAL";
    }

    /**
     * Obtains the oval's dimensions in the "horizontalRadius x verticalRadius" format.
     *
     * @return a string that represents the oval's dimensions.
     */
    @Override
    public String getDimensions() {
        return horizontalRadius + "x" + verticalRadius;
    }

    /**
     * Gets the horizontal radius of the oval.
     *
     * @return The horizontal radius.
     */
    protected int getHorizontalRadius() {
        return horizontalRadius;
    }

    /**
     * Gets the vertical radius of the oval.
     *
     * @return The vertical radius of the oval.
     */
    protected int getVerticalRadius() {
        return verticalRadius;
    }
}

/**
 * represents a particular kind of COval, which is a circle form.
 */
class CCircle extends COval {
    /**
     * Constructs a circle shape with a specified radius.
     *
     * @param radius The radius of the circle.
     */
    public CCircle(int radius) {
        super(radius, radius);
    }

    /**
     * Obtains the shape's type, "CIRCLE" for CCircle.
     *
     * @return the "CIRCLE" string.
     */
    @Override
    public String getType() {
        return "CIRCLE";
    }

    /**
     * Obtains the circle's measurements in the "radius" format.
     *
     * @return A string representing the dimensions of the circle.
     */
    @Override
    public String getDimensions() {
        return Integer.toString(getHorizontalRadius());
    }
}

/**
 * represents a particular kind of CShape, a rectangular form.
 */
class CRectangle extends CShape {
    private int length;
    private int width;

    /**
     * creates a rectangle shape with the given width and length.
     *
     * @param length The length of the rectangle.
     * @param width  The width of the rectangle.
     */
    public CRectangle(int length, int width) {
        super();
        this.length = Math.max(1, Math.min(length, 100));
        this.width = Math.max(1, Math.min(width, 100));
    }

    /**
     * obtains the shape's type, which for CRectangle is "RECTANGLE."
     *
     * @return The string "RECTANGLE".
     */
    @Override
    public String getType() {
        return "RECTANGLE";
    }

    /**
     * Obtains the rectangle's measurements in the "length x width" format.
     *
     * @return A string representing the dimensions of the rectangle.
     */
    @Override
    public String getDimensions() {
        return length + "x" + width;
    }
}

/**
 * represents a particular kind of CRectangle, a square shape.
 */
class CSquare extends CRectangle {
    /**
     * builds a square form with a given side length.
     *
     * @param side The side length of the square.
     */
    public CSquare(int side) {
        super(side, side);
    }

    /**
     * obtains the shape's type, which for CSquare is "SQUARE."
     *
     * @return The string "SQUARE".
     */
    @Override
    public String getType() {
        return "SQUARE";
    }
}

/**
 * Represents a canvas that can hold various shapes.
 */
class CCanvas {
    private List<CShape> shapes;

    /**
     * Constructs an empty canvas with no shapes.
     */
    public CCanvas() {
        this.shapes = new ArrayList<>();
    }

    /**
     * Adds a shape to the canvas.
     *
     * @param shape The shape to be added to the canvas.
     */
    public void addShape(CShape shape) {
        shapes.add(shape);
    }

    /**
     * Gets the list of shapes on the canvas.
     *
     * @return A list containing the shapes on the canvas.
     */
    public List<CShape> getShapes() {
        return shapes;
    }
}

/**
 * A class that demonstrates the creation of random shapes on a canvas.
 */
public class Shapes {
    /**
     * uses a random object as input to generate a random form.
     *
     * @param random The Random object used to generate random values.
     * @return A randomly generated CShape.
     * @throws IllegalStateException in the event that when creating a form, an unexpected value is found.
     */
    private static CShape generateRandomShape(Random random) {
        int choice = random.nextInt(4);
        switch (choice) {
            case 0:
                return new COval(random.nextInt(100) + 1, random.nextInt(100) + 1);
            case 1:
                return new CCircle(random.nextInt(100) + 1);
            case 2:
                return new CRectangle(random.nextInt(100) + 1, random.nextInt(100) + 1);
            case 3:
                return new CSquare(random.nextInt(100) + 1);
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    /**
     * The main method that starts with a canvas and adds random shapes to it.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        CCanvas canvas = new CCanvas();
        Set<String> generatedShapes = new HashSet<>();

        Random random = new Random();
        while (canvas.getShapes().size() < 10) {
            CShape shape = generateRandomShape(random);
            String shapeInfo = shape.getType() + " " + shape.getDimensions();

            if (!generatedShapes.contains(shapeInfo)) {
                canvas.addShape(shape);
                generatedShapes.add(shapeInfo);
            }
        }

        System.out.println("Canvas has the following random shapes:\n");
        for (CShape shape : canvas.getShapes()) {
            System.out.println("Shape " + shape.getId() + ": " + shape.getType() + " " + shape.getDimensions());
        }
    }
}
