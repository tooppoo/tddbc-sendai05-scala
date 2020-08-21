package com.tooppoo

case class TwoDimensionGridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: TwoDimensionGridPoint): Boolean =
    isWestOf(other) ||
    isEastOf(other) ||
    isNorthOf(other) ||
    isSouthOf(other)

  protected def isWestOf(other: TwoDimensionGridPoint): Boolean =
    awayHorizontallyFrom(other, -1) && onSameHorizontalLineWith(other)
  protected def isEastOf(other: TwoDimensionGridPoint): Boolean =
    awayHorizontallyFrom(other, 1) && onSameHorizontalLineWith(other)

  protected def isNorthOf(other: TwoDimensionGridPoint): Boolean =
    onSameVerticalLineWith(other) && awayVerticallyFrom(other, -1)
  protected def isSouthOf(other: TwoDimensionGridPoint): Boolean =
    onSameVerticalLineWith(other) && awayVerticallyFrom(other, 1)

  protected def awayHorizontallyFrom(point: TwoDimensionGridPoint, distance: Int): Boolean = point.x - x == distance
  protected def awayVerticallyFrom(point: TwoDimensionGridPoint, distance: Int): Boolean = point.y - y == distance

  protected def onSameHorizontalLineWith(other: TwoDimensionGridPoint): Boolean = y == other.y
  protected def onSameVerticalLineWith(other: TwoDimensionGridPoint): Boolean = x == other.x
}

object GridPoint {
  def apply(x: Int, y: Int): TwoDimensionGridPoint = TwoDimensionGridPoint(x, y)
}
