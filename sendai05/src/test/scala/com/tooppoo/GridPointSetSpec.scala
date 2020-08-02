package com.tooppoo

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.prop.TableDrivenPropertyChecks

/**
 * [ ] 異なる座標を持つ２つの格子点を含む格子点集合(grid points)を導入します。
 *   [x] GridPointSet定義
 *   [x] GridPointSetは２つの格子点を持つ
 *     [x] 2つの格子点が異なる座標を持つ
 *     [x] 2つの格子点が同じ座標を持つ
 *   [x] 格子点集合が、指定した格子点を含む(contains)かを判定してください
 *     [x] 格子点Aと一致 : true
 *     [x] 格子点Bと一致 : true
 *     [x] 格子点A/B両方と不一致 : false
 *   [x] 格子点集合が連結している(connected)かを判定してください
 *       (補足) 格子点集合に含まれる２つの格子点が隣り合っている場合に限り、その格子点集合が連結しているものとします
 *     [x] AがBの左
 *     [x] AがBの左上
 *     [x] AがBの上
 *     [x] AがBの右上
 *     [x] AがBの右
 *     [x] AがBの右下
 *     [x] AがBの下
 *     [x] AがBの左下
 */

class GridPointSetSpec extends AnyFunSpec with TableDrivenPropertyChecks {
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
  describe("格子点集合[A, B]が、指定した格子点を含む(contains)か") {
    describe("A: (4,7), B: (2, 5)") {
      val a = GridPoint(4, 7)
      val b = GridPoint(2, 5)
      val set = GridPointSet(a, b)

      val grids = Table(
        ("case name", "grid", "expected"),
        ("Aと一致", GridPoint(4, 7), true),
        ("Bと一致", GridPoint(2, 5), true),
        ("A/B両方と不一致", GridPoint(3, 6), false),
      )

      forAll(grids) { (caseName, grid, expected) =>
        describe(caseName) {
          describe(s"${grid.notation}") {
            assert((set contains grid) == expected)
          }
        }
      }
    }
  }
  describe("格子点集合が連結している(connected)か") {
    val set = Table(
      ("case name", "gird a", "grid b", "expected"),
      ("AがBの左隣", GridPoint(4, 7), GridPoint(5, 7), true),
      ("AがBの左上", GridPoint(4, 8), GridPoint(5, 7), false),
      ("AがBの上", GridPoint(5, 8), GridPoint(5, 7), true),
      ("AがBの右上", GridPoint(6, 8), GridPoint(5, 7), false),
      ("AがBの右", GridPoint(6, 7), GridPoint(5, 7), true),
      ("AがBの右下", GridPoint(6, 6), GridPoint(5, 7), false),
      ("AがBの下", GridPoint(5, 6), GridPoint(5, 7), true),
      ("AがBの左下", GridPoint(4, 6), GridPoint(5, 7), false),
    )

    forAll(set) { (caseName, gridA, gridB, expected) =>
      describe(caseName) {
        describe(s"A: ${gridA.notation} B: ${gridB.notation}") {
          it(s"$expected") {
            val set = GridPointSet(gridA, gridB)

            assert(set.isConnected == expected)
          }
        }
      }
    }
  }
}
