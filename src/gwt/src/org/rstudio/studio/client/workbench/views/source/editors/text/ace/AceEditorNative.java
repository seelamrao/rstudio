package org.rstudio.studio.client.workbench.views.source.editors.text.ace;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Command;
import org.rstudio.core.client.CommandWithArg;
import org.rstudio.core.client.events.NativeKeyDownEvent;
import org.rstudio.core.client.events.NativeKeyPressEvent;

public class AceEditorNative extends JavaScriptObject {

   protected AceEditorNative() {}

   public native final EditSession getSession() /*-{
      return this.getSession();
   }-*/;

   public native final Renderer getRenderer() /*-{
      return this.renderer;
   }-*/;

   public native final void resize() /*-{
      this.resize();
   }-*/;

   public native final void setShowPrintMargin(boolean show) /*-{
      this.setShowPrintMargin(show);
   }-*/;

   public native final void setPrintMarginColumn(int column) /*-{
      this.setPrintMarginColumn(column);
   }-*/;

   public native final void setHighlightActiveLine(boolean highlight) /*-{
      this.setHighlightActiveLine(highlight);
   }-*/;

   public native final void focus() /*-{
      this.focus();
   }-*/;

   public native final void blur() /*-{
      this.blur();
   }-*/;

   public native final void setKeyboardHandler(KeyboardHandler keyboardHandler) /*-{
      this.setKeyboardHandler(keyboardHandler);
   }-*/;

   public native final void onChange(Command command) /*-{
      this.getSession().on("change",
              $entry(function () {
                 command.@com.google.gwt.user.client.Command::execute()();
              }));
   }-*/;

   public native final void onKeyDown(HasHandlers handlers) /*-{
      var event = $wnd.require("pilot/event");
      event.addListener(this.textInput.getElement(), "keydown", $entry(function(e) {
         return @org.rstudio.studio.client.workbench.views.source.editors.text.ace.AceEditorNative::fireKeyDown(Lcom/google/gwt/dom/client/NativeEvent;Lcom/google/gwt/event/shared/HasHandlers;)(e, handlers);
      }));
   }-*/;

   public native final void onKeyPress(HasHandlers handlers) /*-{
      var event = $wnd.require("pilot/event");
      event.addListener(this.textInput.getElement(), "keypress", $entry(function(e) {
         return @org.rstudio.studio.client.workbench.views.source.editors.text.ace.AceEditorNative::fireKeyPress(Lcom/google/gwt/dom/client/NativeEvent;Lcom/google/gwt/event/shared/HasHandlers;)(e, handlers);
      }));
   }-*/;

   @SuppressWarnings("unused")
   private static boolean fireKeyDown(NativeEvent event,
                                      HasHandlers handlers)
   {
      return !NativeKeyDownEvent.fire(event, handlers);
   }

   @SuppressWarnings("unused")
   private static boolean fireKeyPress(NativeEvent event,
                                       HasHandlers handlers)
   {
      return !NativeKeyPressEvent.fire(event, handlers);
   }

   public static native void createEnvironment(
         CommandWithArg<JavaScriptObject> callback) /*-{
      var require = $wnd.require;

      var config = {
          paths: {
              ace: "../lib/ace",
              pilot: "../support/pilot/lib/pilot",
              mode: "../../js/acemode",
              theme: "../../js/acetheme"
          }
      };

      var deps = [ "pilot/fixoldbrowsers",
                   "pilot/plugin_manager",
                   "pilot/settings",
                   "pilot/environment",
                   "mode/r",
                   "mode/tex",
                   "mode/sweave",
                   "theme/default"];

      require(config);
      require(deps, $entry(function() {
          var catalog = require("pilot/plugin_manager").catalog;
          catalog.registerPlugins([ "pilot/index" ]).then($entry(function() {
              var env = require("pilot/environment").create();
              catalog.startupPlugins({ env: env }).then($entry(function() {
                  callback.@org.rstudio.core.client.CommandWithArg::execute(Ljava/lang/Object;)(env);
              }));
          }));
      }));
   }-*/;

   public static native AceEditorNative createEditor(
         JavaScriptObject env,
         Element container) /*-{
      var require = $wnd.require;
      var Editor = require("ace/editor").Editor;
      var Renderer = require("ace/virtual_renderer").VirtualRenderer;
      var UndoManager = require("ace/undomanager").UndoManager;

      var TextMode = require("ace/mode/text").Mode;
      var theme = require("theme/default");

      env.editor = new Editor(new Renderer(container, theme));
      var session = env.editor.getSession();
      session.setMode(new TextMode());
      session.setUndoManager(new UndoManager());
      session.setUseSoftTabs(true);
      session.setTabSize(2);

      return env.editor;
   }-*/;
}
