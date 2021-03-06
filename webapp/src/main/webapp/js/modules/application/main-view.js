/**
 * The main view of the whole application
 */
define([
  "dojo/_base/declare",
  "dojo/i18n",
  "dijit/_WidgetBase",
  "dijit/_TemplatedMixin",
  "dijit/_WidgetsInTemplateMixin",
  "dojo/text!./templates/main-page.tmpl",
  "dojo/i18n!./nls/main-view",
  "books/application/user-area/user-area"], function(declare, i18n, _WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin, template) {
  return declare([_WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin], {
    /** The template string to render */
    templateString: template,
    /**
     * Handler after properties are mixed in but before the widget is rendered.
     * Set the messages to the i18n strings that have been loaded
     * @method postMixInProperties
     * @protected
     */
    postMixInProperties: function() {
      this.inherited(arguments);
      this.messages = i18n.getLocalization("books.application", "main-view", this.lang);
    }
  });
});
