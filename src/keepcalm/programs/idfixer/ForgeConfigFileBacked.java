package keepcalm.programs.idfixer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


public class ForgeConfigFileBacked {
	private File file;
	//private List<File> files;
	//private Iterator<File> fileIt;
	//private static final Pattern CONFIG_START = Pattern.compile("START: \"([^\\\"]+)\"");
    //private static final Pattern CONFIG_END = Pattern.compile("END: \"([^\\\"]+)\"");
    public static final String ALLOWED_CHARS = "._-";
    public String fileName;
    public Map<String, Map<String, Property>> categories = new TreeMap<String, Map<String, Property>>();
	
	public ForgeConfigFileBacked(File file) {
		this.file = file;
		
	}

	public Map<String,Property> getBlockProperties() {
		return categories.get("block");
	}
	
	public Map<String,Property> getItemProperties() {
		return categories.get("item");
	}
	public Map<String,Property> getPropertiesForCategory(String name) {
		return categories.get(name);
	}
	

	public void load() throws GuiNonFatalException, IOException {
		File theFile = file;
		FileReader reader;
		try {
			reader = new FileReader(theFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		BufferedReader in = new BufferedReader(reader);
		Map<String,Property> currentMap = null;// new TreeMap<String, Map<String, Integer>>();
		
		while (true) {
			String line = in.readLine();
			if (line == null) {
				break;
			}
			//Matcher start = CONFIG_START.matcher(line);
			//Matcher end = CONFIG_END.matcher(line);
			
			int nameStart = -1, nameEnd = -1;
            boolean skip = false;
            boolean quoted = false;
            for (int i = 0; i < line.length() && !skip; ++i)
            {
                if (Character.isLetterOrDigit(line.charAt(i)) || ALLOWED_CHARS.indexOf(line.charAt(i)) != -1 || (quoted && line.charAt(i) != '"'))
                {
                    if (nameStart == -1)
                    {
                        nameStart = i;
                    }

                    nameEnd = i;
                }
                else if (Character.isWhitespace(line.charAt(i)))
                {
                    // ignore space charaters
                }
                else
                {
                    switch (line.charAt(i))
                    {
                        case '#':
                            skip = true;
                            continue;

                        case '"':
                            if (quoted)
                            {
                                quoted = false;
                            }
                            if (!quoted && nameStart == -1)
                            {
                                quoted = true;
                            }
                            break;

                        case '{':
                            String scopeName = line.substring(nameStart, nameEnd + 1);

                            currentMap = categories.get(scopeName);
                            if (currentMap == null)
                            {
                                currentMap = new TreeMap<String, Property>();
                                categories.put(scopeName, currentMap);
                            }

                            break;

                        case '}':
                            currentMap = null;
                            break;

                        case '=':
                            String propertyName = line.substring(nameStart, nameEnd + 1);

                            if (currentMap == null)
                            {
                                throw new RuntimeException("property " + propertyName + " has no scope");
                            }

                            Property prop = new Property();
                            prop.setName(propertyName);
                            prop.value = line.substring(i + 1);
                            i = line.length();

                            currentMap.put(propertyName, prop);

                            break;

                        default:
                        	in.close();
                            throw new RuntimeException("unknown character " + line.charAt(i));
                    }
                }
            }
            if (quoted)
            {
            	in.close();
                throw new RuntimeException("unmatched quote");
            }
		}
		in.close();
		
		
		
	}
	
	public void save() {
		/*if (PARENT != null && PARENT != this)
        {
            PARENT.save();
            return;
        }*/

        try
        {
            if (file.getParentFile() != null)
            {
                file.getParentFile().mkdirs();
            }

            if (!file.exists() && !file.createNewFile())
            {
                return;
            }

            if (file.canWrite())
            {
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(fos));

                buffer.write("# Configuration file\r\n");
                buffer.write("# Generated on " + DateFormat.getInstance().format(new Date()) + "\r\n");
                buffer.write("\r\n");

                /*if (children.isEmpty())
                {
                    save(buffer);
                }*/
                
                
                   // for (Map.Entry<String, Configuration> entry : children.entrySet())
                    //{
                    //    buffer.write("START: \"" + entry.getKey() + "\"\r\n");
                    //    entry.getValue().save(buffer);
                    //    buffer.write("END: \"" + entry.getKey() + "\"\r\n\r\n");
                    //}
                

                buffer.close();
                fos.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	}

}
