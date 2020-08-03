package com.tooppoo

case class TwoDimensionGridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: TwoDimensionGridPoint): Boolean =
    isLeftOf(other) ||
    isRightOf(other) ||
    isUpOf(other) ||
    isDownOf(other)

  private def isLeftOf(other: TwoDimensionGridPoint): Boolean = x - 1 == other.x && y == other.y
  private def isRightOf(other: TwoDimensionGridPoint): Boolean = x + 1 == other.x && y == other.y
  private def isUpOf(other: TwoDimensionGridPoint): Boolean = x == other.x && y - 1 == other.y
  private def isDownOf(other: TwoDimensionGridPoint): Boolean = x == other.x && y + 1 == other.y
}
