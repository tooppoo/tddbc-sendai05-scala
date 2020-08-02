package com.tooppoo

class GridPointSet private (g1: GridPoint, g2: GridPoint, grids: GridPoint*) {
  private val allGrids: Seq[GridPoint] = Seq(g1, g2) concat grids

  if (existDuplicateGrid) {
    throw new IllegalArgumentException("g1 and g2 must not same")
  }

  def contains(grid: GridPoint): Boolean = allGrids.contains(grid)

  def isConnected: Boolean = g1 isNeighborOf g2

  private def existDuplicateGrid: Boolean = {
    val groupedByNotation = allGrids.groupBy(g => g.notation)

    // 重複が無ければ一致するはず
    allGrids.size != groupedByNotation.size
  }
}

object GridPointSet {
  def apply(g1: GridPoint, g2: GridPoint) =
    new GridPointSet(g1, g2)
  def apply(g1: GridPoint, g2: GridPoint, grids: GridPoint*) =
    new GridPointSet(g1, g2, grids: _*)
}
