package com.tooppoo

import org.scalatest.funspec.AnyFunSpec

/**
 * [x] x座標とy座標を与えて格子点を生成してください
 * [x] 生成した格子点からx座標とy座標を取得してください
 * [x] 生成した格子点から文字列表記 (notation) を取得してください
 * [ ] ２つの格子点が同じ座標(coordinates)を持つか判定してください
 *   [ ] ==実装
 *   [ ] x/y座標が一致→true
 *   [ ] x座標だけ同じ→false
 *   [ ] y座標だけ同じ→false
 *   [ ] x/y座標両方違う→false
 */

class GridPointSpec extends AnyFunSpec {
  describe("文字列表記") {
    describe("x = 4, y = 7") {
      val p4_7 = GridPoint(4, 7)

      it("'(4,7)'") {
        assert(p4_7.notation == "(4,7)")
      }
    }
  }
  describe("座標取得") {
    describe("x = 4, y = 7") {
      val p4_7 = GridPoint(4, 7)

      describe("x座標") {
        it("4") {
          assert(p4_7.x == 4)
        }
      }
      describe("y座標") {
        it("7") {
          assert(p4_7.y == 7)
        }
      }
    }
  }
}
