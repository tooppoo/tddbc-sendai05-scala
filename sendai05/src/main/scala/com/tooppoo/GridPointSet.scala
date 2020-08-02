package com.tooppoo

class GridPointSet private (g1: GridPoint, g2: GridPoint, grids: GridPoint*) {
  private val allGrids: Seq[GridPoint] = Seq(g1, g2) concat grids

  if (existDuplicateGrid) {
    throw new IllegalArgumentException("g1 and g2 must not same")
  }

  def contains(grid: GridPoint): Boolean = allGrids.contains(grid)

  def isConnected: Boolean = allGrids match {
    case g1 :: g2 :: Nil => g1 isNeighborOf g2
    case g1 :: g2 :: g3 :: _  if g1 isNeighborOf g2 =>
      (g1 isNeighborOf g3) || (g2 isNeighborOf g3)
    case g1 :: g2 :: g3 :: _  if g1 isNeighborOf g3 =>
      (g1 isNeighborOf g2) || (g2 isNeighborOf g3)
    case g1 :: g2 :: g3 :: _  if g2 isNeighborOf g3 =>
      (g1 isNeighborOf g2) || (g1 isNeighborOf g3)
    case _ => false
  }

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
