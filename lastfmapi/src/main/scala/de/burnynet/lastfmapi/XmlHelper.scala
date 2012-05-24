package de.burnynet.lastfmapi

import xml.{Node, NodeSeq}


/**
 * Some utilities for xml extraction
 * @author tobi
 */

object XmlHelper {


  /**
   * From a list of nodes and a Node-To-Object conversion function, create a list of objects
   * @param nodes The selected nodes
   * @param f The conversion function which takes an xml node and returns the Object of type T
   * @tparam T The type of object returned
   * @return
   */
  def fetchList[T](nodes:NodeSeq, f: (NodeSeq) => T) : List[T] = nodes map {i:Node =>f(i)} toList
}
