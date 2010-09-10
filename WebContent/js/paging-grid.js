Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '/extjs-crud-grid/ext-3.2.1/resources/images/default/s.gif';
	
    var store = new Ext.data.JsonStore({
        root: 'data',
        totalProperty: 'total',
        remoteSort: true,

        fields: [
			{ name: 'id'},
			{ name: 'name', type: 'string' },
			{ name: 'phone',type: 'string' },
			{ name: 'email', type: 'string' }
        ],
        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
        proxy: new Ext.data.HttpProxy({
            url: 'contact/view.action'
        })
    });
    
    store.setDefaultSort('name', 'asc');
    
    // trigger the data store load
    store.load({params:{start:0, limit:15}});
    
    // create grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {header: "NAME",
             width: 180,
             sortable: true,
             dataIndex: 'name',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "PHONE #",
             width: 170,
             sortable: true,
             dataIndex: 'phone',
             editor: {
                 xtype: 'textfield',
                 allowBlank: false
            }},
            {header: "EMAIL",
             width: 200,
             sortable: true,
             dataIndex: 'email',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }}
        ],
        viewConfig:{forcefit:true},
        title: 'My Contacts',
        height: 300,
        width:600,
		frame:true,
		// paging bar on the bottom - if you want to place it on the top, use tbar instead of bbar
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true,
            displayMsg: 'Displaying contacts {0} - {1} of {2}',
            emptyMsg: "No contacts to display",
            plugins : [new Ext.ux.plugin.PagingToolbarResizer({ //Plugin page: http://loianegroner.com/extjs-plugins/pagingtoolbarresizer/
	            		options: [ 5, 10, 15, 20, 25 ], 
	            		prependCombo: false,
	            		displayText: 'Contacts per Page'
            		})]
        })
    });

    //render to DIV
    grid.render('paging-grid');
});