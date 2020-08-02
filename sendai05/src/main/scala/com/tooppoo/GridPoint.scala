package com.tooppoo

case class GridPoint(x: Int, y: Int) {
  def notation = s"($x,$y)"

  def isNeighborOf(other: GridPoint): Boolean = {
    x - 1 == other.x ||
      x + 1 == other.x ||
      y - 1 == other.y
  }
}
