package com.tooppoo

import org.scalatest.funspec.AnyFunSpec

/**
 * [ ] 異なる座標を持つ２つの格子点を含む格子点集合(grid points)を導入します。
 *   [x] GridPointSet定義
 *   [x] GridPointSetは２つの格子点を持つ
 *     [x] 2つの格子点が異なる座標を持つ
 *     [x] 2つの格子点が同じ座標を持つ
 *   [ ] 格子点集合が、指定した格子点を含む(contains)かを判定してください
 *   [ ] 格子点集合が連結している(connected)かを判定してください
 *       (補足) 格子点集合に含まれる２つの格子点が隣り合っている場合に限り、その格子点集合が連結しているものとします
 */

class GridPointSetSpec extends AnyFunSpec {
  describe("格子点集合の生成") {
    describe("2つの格子点が異なる座標を持つ") {
      it("生成できる") {
        val g1 = GridPoint(4, 7)
        val g2 = GridPoint(3, 6)

        GridPointSet(g1, g2)
      }
    }
    describe("2つの格子点が同じ座標を持つ") {
      it("生成できない") {
        val g1 = GridPoint(4, 7)
        val g2 = GridPoint(4, 7)

        assertThrows[IllegalArgumentException] {
          GridPointSet(g1, g2)
        }
      }
    }
  }
}
