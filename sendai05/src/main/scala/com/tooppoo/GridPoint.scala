package com.tooppoo

case class TwoDimensionGridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: TwoDimensionGridPoint): Boolean =
    isWestOf(other) ||
    isEastOf(other) ||
    isNorthOf(other) ||
    isSouthOf(other)

  protected def isWestOf(other: TwoDimensionGridPoint): Boolean =
    awayFrom(other, Direction.toWest(1)) && onSameHorizontalLineWith(other)

  protected def isEastOf(other: TwoDimensionGridPoint): Boolean =
    awayFrom(other, Direction.toEast(1)) && onSameHorizontalLineWith(other)

  protected def isNorthOf(other: TwoDimensionGridPoint): Boolean =
    onSameVerticalLineWith(other) && awayFrom(other, Direction.toNorth(1))

  protected def isSouthOf(other: TwoDimensionGridPoint): Boolean =
    onSameVerticalLineWith(other) && awayFrom(other, Direction.toSouth(1))

  private def awayFrom(other: TwoDimensionGridPoint, direction: Direction): Boolean =
    direction test (from = other, to = this)
  protected def onSameHorizontalLineWith(other: TwoDimensionGridPoint): Boolean = y == other.y
  protected def onSameVerticalLineWith(other: TwoDimensionGridPoint): Boolean = x == other.x

  private trait Direction {
    def test(from: TwoDimensionGridPoint, to: TwoDimensionGridPoint): Boolean
  }
  private object Direction {
    final class West(private val distance: Int) extends Direction {
      def test(from: TwoDimensionGridPoint, to: TwoDimensionGridPoint): Boolean =
        from.x - distance == to.x
    }
    final class East(private val distance: Int) extends Direction {
      def test(from: TwoDimensionGridPoint, to: TwoDimensionGridPoint): Boolean =
        from.x + distance == to.x
    }
    final class North(private val distance: Int) extends Direction {
      def test(from: TwoDimensionGridPoint, to: TwoDimensionGridPoint): Boolean =
        from.y + distance == to.y
    }
    final class South(private val distance: Int) extends Direction {
      def test(from: TwoDimensionGridPoint, to: TwoDimensionGridPoint): Boolean =
        from.y - distance == to.y
    }

    def toWest(d: Int) = new West(d)
    def toEast(d: Int) = new East(d)
    def toNorth(d: Int) = new North(d)
    def toSouth(d: Int) = new South(d)
  }
}

object GridPoint {
  def apply(x: Int, y: Int): TwoDimensionGridPoint = TwoDimensionGridPoint(x, y)
}
