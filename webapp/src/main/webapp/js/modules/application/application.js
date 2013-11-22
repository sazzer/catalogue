/**
 * The main application. 
 */
define([
    "dojo/_base/window", 
    "books/application/main-view",
    "dojo/domReady!"], function(window, MainView) {
  var mainView = new MainView({});
  mainView.placeAt(window.body());
});
