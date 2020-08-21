package com.tooppoo

case class TwoDimensionGridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: TwoDimensionGridPoint): Boolean =
    isWestOf(other) ||
    isEastOf(other) ||
    isNorthOf(other) ||
    isSouthOf(other)

  protected def isWestOf(other: TwoDimensionGridPoint): Boolean = x - 1 == other.x && y == other.y
  protected def isEastOf(other: TwoDimensionGridPoint): Boolean = x + 1 == other.x && y == other.y
  protected def isNorthOf(other: TwoDimensionGridPoint): Boolean = x == other.x && y - 1 == other.y
  protected def isSouthOf(other: TwoDimensionGridPoint): Boolean = x == other.x && y + 1 == other.y
}

object GridPoint {
  def apply(x: Int, y: Int): TwoDimensionGridPoint = TwoDimensionGridPoint(x, y)
}
