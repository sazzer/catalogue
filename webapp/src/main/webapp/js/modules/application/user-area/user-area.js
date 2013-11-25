/**
 * The User Area in the header of the whole application
 */
define([
  "dojo/_base/declare",
  "dojo/i18n",
  "dijit/_WidgetBase",
  "dijit/_TemplatedMixin",
  "books/application/user-area/login-dialog",
  "dojo/text!./templates/user-area.tmpl",
  "dojo/i18n!./nls/user-area"
  ], function(declare, i18n, _WidgetBase, _TemplatedMixin, LoginDialog, template) {
  return declare([_WidgetBase, _TemplatedMixin], {
    /** The template string to render */
    templateString: template,
    /** The Login Dialog to use */
    _loginDialog: new LoginDialog(),
    /**
     * Handler after properties are mixed in but before the widget is rendered.
     * Set the messages to the i18n strings that have been loaded
     * @method postMixInProperties
     * @protected
     */
    postMixInProperties: function() {
      this.inherited(arguments);
      this.messages = i18n.getLocalization("books.application.user-area", "user-area", this.lang);
    },

    /**
     * Handle when the Login link is clicked
     * @method _onClickLogin
     * @param e The event from clicking the link
     * @private
     */
    _onClickLogin: function(e) {
      e.preventDefault();
      this._loginDialog.show();
    }
  });
});

