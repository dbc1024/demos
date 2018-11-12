package com.ectrip.ec.noteBook.service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.model.notebook.Notebook;
import com.ectrip.ec.noteBook.dao.idao.INotebookDAO;
import com.ectrip.ec.noteBook.service.iservice.INotebookService;

public class NotebookService extends GenericService implements INotebookService{
     INotebookDAO notebookDao;

	public INotebookDAO getNotebookDao() {
		return notebookDao;
	}

	public void setNotebookDao(INotebookDAO notebookDao) {
		this.notebookDao = notebookDao;
	}

	public void notebookSave(Notebook notebook) { 
		notebookDao.notebookSave(notebook);
	}

	public void delNotebooks(String[] noteids) {
		notebookDao.delNotebooks(noteids);
		
	}
}
