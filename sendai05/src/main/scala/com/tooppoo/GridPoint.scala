package com.tooppoo

case class GridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: GridPoint): Boolean =
    isLeftFrom(other) ||
    isRightFrom(other) ||
    isUpFrom(other) ||
    isDownFrom(other)

  private def isLeftFrom(other: GridPoint): Boolean = x - 1 == other.x && ySameWith(other)
  private def isRightFrom(other: GridPoint): Boolean = x + 1 == other.x && ySameWith(other)
  private def isUpFrom(other: GridPoint): Boolean = xSameWith(other) && y - 1 == other.y
  private def isDownFrom(other: GridPoint): Boolean = xSameWith(other) && y + 1 == other.y

  private def xSameWith(other: GridPoint): Boolean = x == other.x
  private def ySameWith(other: GridPoint): Boolean = y == other.y
}
