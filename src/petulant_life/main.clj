(ns petulant-life.main
  (:import (org.lwjgl.opengl DisplayMode
                             Display
                             GL11
                             ContextAttribs
                             PixelFormat))

  (:require [petulant-life.rendering :as r]))

; Defining some width and height constraints to the program.
(def width 640)
(def height 480)

; Initializing the OpenGL window.
(defn create []
  (Display/setDisplayMode (DisplayMode. width height))
  (Display/create (PixelFormat.)
                  (-> (ContextAttribs. 3 2)
                      (.withForwardCompatible    true)
                      (.withProfileCore true)))
  (Display/setTitle "Petulant Life")

  (GL11/glClearColor 0.9 0.9 0.9 1))

; Cleaning up the LWJGL context.
(defn destroy []
  (Display/destroy))

; Running the simulation / graphics.
(defn run []
  (loop [running true]
    (if (and running (not (Display/isCloseRequested)))
      (do (GL11/glClear GL11/GL_COLOR_BUFFER_BIT)
          (r/draw-rectangles [[10 10 50 50]
                              [70 10 50 50]
                              [10 70 50 50]
                              [70 70 50 50]] nil)
          (Display/update)
          (recur true))
      nil)))

; Entry point.
(defn -main [& args]
  (create)
  (run)
  (destroy))
