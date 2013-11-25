/**
 * The User Area in the header of the whole application
 */
define([
  "dojo/_base/declare",
  "dojo/i18n",
  "dijit/_WidgetBase",
  "dijit/_TemplatedMixin",
  "dojo/text!./templates/user-area.tmpl",
  "dojo/i18n!./nls/user-area"
  ], function(declare, i18n, _WidgetBase, _TemplatedMixin, template) {
  return declare([_WidgetBase, _TemplatedMixin], {
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
      this.messages = i18n.getLocalization("books.application.user-area", "user-area", this.lang);
    }
  });
});

