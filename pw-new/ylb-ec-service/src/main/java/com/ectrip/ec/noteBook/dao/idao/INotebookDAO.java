package com.ectrip.ec.noteBook.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.notebook.Notebook;

public interface INotebookDAO extends IGenericDao{

	void notebookSave(Notebook notebook);

	void delNotebooks(String[] noteids);

}
