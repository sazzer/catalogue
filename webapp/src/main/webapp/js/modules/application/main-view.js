/**
 * The main view of the whole application
 */
define([
  "dojo/_base/declare",
  "dojo/i18n",
  "dijit/_WidgetBase",
  "dijit/_TemplatedMixin",
  "dojo/text!./templates/main-page.tmpl",
  "dojo/i18n!./nls/main-view"], function(declare, i18n, _WidgetBase, _TemplatedMixin, template) {
  return declare([_WidgetBase, _TemplatedMixin], {
    templateString: template,
    postMixInProperties: function() {
      this.inherited(arguments);
      this.messages = i18n.getLocalization("books.application", "main-view", this.lang);
    }
  });
});
