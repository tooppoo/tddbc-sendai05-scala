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
    direction match {
      case Direction.West(d) => other.x - d == x
      case Direction.East(d) => other.x + d == x
      case Direction.North(d) => other.y + d == y
      case Direction.South(d) => other.y - d == y
    }
  protected def onSameHorizontalLineWith(other: TwoDimensionGridPoint): Boolean = y == other.y
  protected def onSameVerticalLineWith(other: TwoDimensionGridPoint): Boolean = x == other.x

  private trait Direction {
    val distance: Int
  }
  private object Direction {
    case class West(distance: Int) extends Direction
    case class East(distance: Int) extends Direction
    case class North(distance: Int) extends Direction
    case class South(distance: Int) extends Direction

    def toWest(d: Int): Direction = West(d)
    def toEast(d: Int): Direction = East(d)
    def toNorth(d: Int): Direction = North(d)
    def toSouth(d: Int): Direction = South(d)
  }
}

object GridPoint {
  def apply(x: Int, y: Int): TwoDimensionGridPoint = TwoDimensionGridPoint(x, y)
}
