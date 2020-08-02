package com.tooppoo

case class GridPointSet(g1: GridPoint, g2: GridPoint) {
  def contains(grid: GridPoint): Boolean = g1 == grid || g2 == grid

  if (g1 == g2) throw new IllegalArgumentException("g1 and g2 must not same")
}
