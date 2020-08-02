package com.tooppoo

import org.scalatest.funspec.AnyFunSpec

class HelloSpec extends AnyFunSpec {
  describe("hello object") {
    it("say hello") {
      assert(Hello.greeting == "hello")
    }
  }
}
