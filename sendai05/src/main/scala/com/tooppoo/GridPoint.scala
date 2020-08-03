package com.tooppoo

trait WithXY {
  val x: Int
  val y: Int

  protected def isWestOf(other: WithXY): Boolean = x - 1 == other.x && y == other.y
  protected def isEastOf(other: WithXY): Boolean = x + 1 == other.x && y == other.y
  protected def isNorthOf(other: WithXY): Boolean = x == other.x && y - 1 == other.y
  protected def isSouthOf(other: WithXY): Boolean = x == other.x && y + 1 == other.y
}

case class TwoDimensionGridPoint(x: Int, y: Int) extends WithXY {
  def notation = s"($x,$y)"

  def isNeighborOf(other: TwoDimensionGridPoint): Boolean =
    isWestOf(other) ||
    isEastOf(other) ||
    isNorthOf(other) ||
    isSouthOf(other)
}

object GridPoint {
  def apply(x: Int, y: Int): TwoDimensionGridPoint = TwoDimensionGridPoint(x, y)
}
