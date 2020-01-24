import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Chess extends JPanel {

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D gr2D = (Graphics2D) gr;
        Point2D.Float pos1 = new Point2D.Float(80, 20);
        Point2D.Float pos2 = new Point2D.Float(20, 100);
        BasicStroke aStroke = new BasicStroke(15.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        gr2D.setStroke(aStroke);
        Line2D.Float line = new Line2D.Float(pos1, pos2);
        gr2D.draw(line);

    }


    public static void main(String[] args) {
        System.out.println("Welcome to Chess!!!");

        // create a board
        Board board = new Board();
        JFrame frame = new JFrame();
        frame.add(new Chess());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);

        while (!board.inCheckmate(true) && !board.inCheckmate(false)) {
            System.out.println("Turn: " + (board.isWhiteTurn ? "White" : "Black"));
            System.out.println(board.getBoard());
            int y1 = input.nextInt();
            int x1 = input.nextInt();
            int y2 = input.nextInt();
            int x2 = input.nextInt();
            if (!board.movePiece(y1, x1, y2, x2)) {
                System.out.println("not valid, try again...");
            }

            if (board.inCheckmate(true)) {
                System.out.println("Black Wins!!!");
            }

            if (board.inCheckmate(false)) {
                System.out.println("White Wins!!!");
            }

        }

    }
}
