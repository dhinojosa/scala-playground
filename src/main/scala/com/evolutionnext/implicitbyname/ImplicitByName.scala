package com.evolutionnext.implicitbyname

class OutputPort[-A] {
    def connectTo(inputPort: InputPort[A]): Unit = {}
}

class InputPort[+A]

object Converter {

    private class ConnectablePort[A](outputPort: OutputPort[A]) {
        def |>(inputPort: InputPort[A]): Unit = outputPort connectTo
            inputPort
    }

    def convert[A](f: (OutputPort[A] => ConnectablePort[A]) => Unit): Unit = {
        def connectablePortWrapper(x: OutputPort[A]): ConnectablePort[A] = new ConnectablePort[A](
            x)

        f(connectablePortWrapper)
    }
}

object MyRunner extends App {

    val output = new OutputPort[Int]
    val input = new InputPort[Int]

    import Converter.convert

    //output |> input  won't work
    convert[Int] { implicit wrapper =>
        output |> input // Should be valid here
    }
}

