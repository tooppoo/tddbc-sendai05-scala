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
 *  [x] 格子点集合を、異なる２つまたは３つの格子点を含むことが出来るように拡張してください
 *     [x] 3つの格子点を受け取れるようにする
 *       [x] 全ての格子点の座標が異なる: OK
 *       [x] 2つの格子点の座標が同じ  : NG
 *         [x] A・B・Cのうち、A・Bが一致
 *         [x] A・B・Cのうち、B・Cが一致
 *         [x] A・B・Cのうち、C・Aが一致
 *       [x] 全ての格子点の座標が同じ  : NG
 *     [x] 格子点を含むか
 *       [x] Aに一致
 *       [x] Bに一致
 *       [x] Cに一致
 *       [x] 全てに一致しない
 *     [ ] 格子点集合に含まれる任意の格子点について
 *         その格子点から隣り合っている格子点のみを経由して
 *         その他全ての格子点へ到達できる場合に限り
 *         その格子点集合が連結しているものとします
 *         →全ての格子点が連結している場合に、集合は連結している
 *         [ ] A,B,C が連結: true
 *         [ ] A,B が連結: false
 *         [ ] B,C が連結: false
 *         [ ] C,B が連結: false
 *         [ ] 連結無し: false
 */

class GridPointSetSpec extends AnyFunSpec with TableDrivenPropertyChecks {
  describe("格子点集合の生成") {
    describe("生成できる") {
      val cases = Table(
        ("case name", "create set"),
        (
          "2つの格子点が異なる座標を持つ",
          () => GridPointSet(GridPoint(4, 7), GridPoint(3, 6))
        ),
        (
          "3つの格子点が異なる座標を持つ",
          () => GridPointSet(GridPoint(4, 7), GridPoint(3, 6), GridPoint(2, 4))
        ),
      )

      forAll(cases) { (caseName, create) =>
        it(caseName) {
          create()

          succeed // 例外が起きなければOK
        }
      }
    }

    describe("生成できない") {
      describe("格子点が2つ") {
        val cases = Table(
          ("case name", "create set"),
          ("2つの格子点が同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(4, 7)))
        )

        forAll(cases) { (caseName, create) =>
          it(caseName) {
            assertThrows[IllegalArgumentException] {
              create()
            }
          }
        }
      }
      describe("格子点が3つ[A, B, C]") {
        val cases = Table(
          ("case name", "create set"),
          ("A・Bが同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(4, 7), GridPoint(3, 2))),
          ("B・Cが同じ座標を持つ", () => GridPointSet(GridPoint(3, 2), GridPoint(4, 7), GridPoint(4, 7))),
          ("C・Aが同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(3, 2), GridPoint(4, 7))),
          ("全て同じ座標を持つ", () => GridPointSet(GridPoint(4, 7), GridPoint(4, 7), GridPoint(4, 7))),
        )

        forAll(cases) { (caseName, create) =>
          it(caseName) {
            assertThrows[IllegalArgumentException] {
              create()
            }
          }
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
    describe("A: (4,7), B: (2, 5), C: (3, 6)") {
      val a = GridPoint(4, 7)
      val b = GridPoint(2, 5)
      val c = GridPoint(3, 6)
      val set = GridPointSet(a, b, c)

      val grids = Table(
        ("case name", "grid", "expected"),
        ("Aと一致", GridPoint(4, 7), true),
        ("Bと一致", GridPoint(2, 5), true),
        ("Cと一致", GridPoint(2, 5), true),
        ("A/B/C全てと不一致", GridPoint(1, 2), false),
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
    describe("格子点がA,Bの2つ") {
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
    describe("格子点がA,B,Cの3つ") {
      val set = Table(
        ("case name", "gird a", "grid b", "grid c", "expected"),
        ("A,B,C全て隣接", GridPoint(4, 7), GridPoint(3, 7), GridPoint(4, 6), true),
        ("A,Bが隣接", GridPoint(4, 7), GridPoint(3, 7), GridPoint(2, 6), false),
      )

      forAll(set) { (caseName, gridA, gridB, gridC, expected) =>
        describe(caseName) {
          describe(
            s"A: ${gridA.notation} B: ${gridB.notation} C: ${gridC.notation}"
          ) {
            it(s"$expected") {
              val set = GridPointSet(gridA, gridB, gridC)

              assert(set.isConnected == expected)
            }
          }
        }
      }
    }
  }
}
