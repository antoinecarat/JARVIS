package plugins.simpleBase.importYamlCreator;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Describes which files should be shown in the file browser. 
 */
public class FileFilterYaml extends FileFilter{
	private String description;
	private String extension;

	private FileFilterYaml(String description, String extension){
		if(description == null || extension ==null){
			throw new NullPointerException();
		}
		this.description = description;
		this.extension = extension;
	}

	public FileFilterYaml(){
		this("Yaml file",".yaml");
	}

	@Override
	public String getDescription(){
		return description;
	}

	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) { 
			return true; 
		} 
		String nomFichier = f.getName().toLowerCase(); 

		return nomFichier.endsWith(extension);
	}
}