/**
 * The main view of the whole application
 */
define([
  "dojo/_base/declare",
  "dijit/Dialog",
  "dijit/_WidgetBase",
  "dijit/_TemplatedMixin",
  "dojo/text!./templates/main-page.tmpl"], function(declare, Dialog, _WidgetBase, _TemplatedMixin, template) {
  return declare([_WidgetBase, _TemplatedMixin], {
    templateString: template,
    _onClick: function() {
      var dialog = new Dialog({
        title: "My Dialog",
        content: "My Content",
        style: {
          width: "300px"
        }
      });
      dialog.show();
    }
  });
});
