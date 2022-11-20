import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

public class Main {
    private static final String[] extensions = { "xml" };
    public static final String PROXY_SERVICES_DIR       = "proxy-services";
    public static final String SEQUENCES_DIR            = "sequences";
    public static final String TEMPLATES_DIR            = "templates";
    public static final String ENDPOINTS_DIR            = "endpoints";
    public static final String LOCAL_ENTRY_DIR          = "local-entries";
    public static final String TASKS_DIR                = "tasks";
    public static final String EVENTS_DIR               = "event-sources";
    public static final String EXECUTORS_DIR            = "priority-executors";
    public static final String MESSAGE_STORE_DIR        = "message-stores";
    public static final String MESSAGE_PROCESSOR_DIR    = "message-processors";
    public static final String REST_API_DIR             = "api";
    public static final String INBOUND_ENDPOINT_DIR     = "inbound-endpoints";
    public static final String SYNAPSE_IMPORTS_DIR      = "imports";
    public static final String SYNAPSE_NAMESPACE        = "http://ws.apache.org/ns/synapse";


    public static void main(String[] args)
    {
        System.out.println( "===========================================================");
        System.out.println( "================== Starting the Client ====================");
        System.out.println( "===========================================================");

        if (args.length == 0) {
            System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Root directory set to null...");
        }
        else{
            String root = args[0];

            System.out.println( "===========================================================");
            System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Tenants...");
            Instant startLoadingTenants = Instant.now();

            File directoryPath = new File(root);
            String tenants[] = directoryPath.list();

            for(int i=0; i<tenants.length; i++) {
                System.out.println( "===========================================================");
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Tenant : " + tenants[i]);
                Instant startLoadingTenant = Instant.now();

                loadSynapseImports(root + "/"+tenants[i] + "/synapse-configs/default");
                loadLocalEntries(root + "/"+tenants[i] + "/synapse-configs/default");
                loadEndpoints(root + "/"+tenants[i] + "/synapse-configs/default");
                loadSequences(root + "/"+tenants[i] + "/synapse-configs/default");
                loadTemplates(root + "/"+tenants[i] + "/synapse-configs/default");
                loadProxyServices(root + "/"+tenants[i] + "/synapse-configs/default");
                loadTasks(root + "/"+tenants[i] + "/synapse-configs/default");
                loadEventSources(root + "/"+tenants[i] + "/synapse-configs/default");
                loadExecutors(root + "/"+tenants[i] + "/synapse-configs/default");
                loadMessageStores(root + "/"+tenants[i] + "/synapse-configs/default");
                loadMessageProcessors(root + "/"+tenants[i] + "/synapse-configs/default");
                loadAPIs(root + "/"+tenants[i] + "/synapse-configs/default");
                loadInboundEndpoint(root + "/"+tenants[i] + "/synapse-configs/default");

                Instant doneLoadingTenant = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Completed loading Tenant : " + tenants[i] + " | Total time : " +Duration.between(startLoadingTenant, doneLoadingTenant).toMillis() + "ms");
                System.out.println( "===========================================================");
            }

            Instant doneLoadingTenants = Instant.now();

            System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Loading tenants completed. | "+ "Number of tenants loaded : " + tenants.length + " | "+ " Total time : "+ Duration.between(startLoadingTenants, doneLoadingTenants).toMillis() +" ms"  );
            System.out.println( "===========================================================");

        }

        System.out.println( "===========================================================");
        System.out.println( "============== Shutting down the Client ===================");
        System.out.println( "===========================================================");
    }

    private static void loadSynapseImports(String rootDirPath)
    {
        Instant start = Instant.now();
        File synImportsDir = new File(rootDirPath , SYNAPSE_IMPORTS_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Synapse Imports");
        int i = 0;
        if (synImportsDir.exists()) {
            Iterator synImports = FileUtils.iterateFiles(synImportsDir, extensions, false);
            while (synImports.hasNext()) {
                File file = (File) synImports.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Synapse Imports" + " | " + "Number of Synapse Imports : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms" );
    }
    private static void loadLocalEntries(String rootDirPath)
    {
        Instant start = Instant.now();
        File localEntriesDir = new File(rootDirPath , LOCAL_ENTRY_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Local Entries");
        int i = 0;
        if (localEntriesDir.exists()) {
            Iterator entryDefinitions = FileUtils.iterateFiles(localEntriesDir, extensions, false);
            while (entryDefinitions.hasNext()) {
                File file = (File) entryDefinitions.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Local Entries"+ " | " + "Number of Local Entries : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadEndpoints(String rootDirPath)
    {
        Instant start = Instant.now();
        File endpointsDir = new File(rootDirPath , ENDPOINTS_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Endpoints");
        int i = 0;
        if (endpointsDir.exists()) {
            Iterator endpoints = FileUtils.iterateFiles(endpointsDir, extensions, false);
            while (endpoints.hasNext()) {
                File file = (File) endpoints.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Endpoints"+ " | " + "Number of Endpoints : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadSequences(String rootDirPath)
    {
        Instant start = Instant.now();
        File sequencesDir = new File(rootDirPath , SEQUENCES_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Sequences");
        int i = 0;
        if (sequencesDir.exists()) {
            Iterator sequences = FileUtils.iterateFiles(sequencesDir, extensions, false);
            while (sequences.hasNext()) {
                File file = (File) sequences.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Sequences"+ " | " + "Number of Sequences : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadTemplates(String rootDirPath)
    {
        Instant start = Instant.now();
        File templatesDir = new File(rootDirPath, TEMPLATES_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Templates");
        int i = 0;
        if (templatesDir.exists()) {
            Iterator templates = FileUtils.iterateFiles(templatesDir, extensions, false);
            while (templates.hasNext()) {
                File file = (File) templates.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    OMElement element = document.getFirstChildWithName(
                            new QName(SYNAPSE_NAMESPACE, "sequence"));
                    if (element != null) {
                        
                    } else {
                        element = document.getFirstChildWithName(
                                new QName(SYNAPSE_NAMESPACE, "endpoint"));
                        
                    }
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Templates"+ " | " + "Number of Templates : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadProxyServices(String rootDirPath)
    {
        Instant start = Instant.now();
        File proxyServicesDir = new File(rootDirPath , PROXY_SERVICES_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Proxy Services");
        int i = 0;
        if (proxyServicesDir.exists()) {
            Iterator proxyDefinitions = FileUtils.iterateFiles(proxyServicesDir, extensions, false);
            while (proxyDefinitions.hasNext()) {
                File file = (File) proxyDefinitions.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Proxy Services"+ " | " + "Number of Proxy Services : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadTasks(String rootDirPath)
    {
        Instant start = Instant.now();
        File tasksDir = new File(rootDirPath , TASKS_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Tasks");
        int i = 0;
        if (tasksDir.exists()) {
            Iterator taskDefinitions = FileUtils.iterateFiles(tasksDir, extensions, false);
            while (taskDefinitions.hasNext()) {
                File file = (File) taskDefinitions.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Tasks"+ " | " + "Number of Tasks : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadEventSources(String rootDirPath)
    {
        Instant start = Instant.now();
        File eventsDir = new File(rootDirPath , EVENTS_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Event Sources");
        int i = 0;
        if (eventsDir.exists()) {
            Iterator events = FileUtils.iterateFiles(eventsDir, extensions, false);
            while (events.hasNext()) {
                File file = (File) events.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Event Sources"+ " | " + "Number of Event Sources : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadExecutors(String rootDirPath)
    {
        Instant start = Instant.now();
        File executorsDir = new File(rootDirPath , EXECUTORS_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Executors");
        int i = 0;
        if (executorsDir.exists()) {
            Iterator executors = FileUtils.iterateFiles(executorsDir, extensions, false);
            while (executors.hasNext()) {
                File file = (File) executors.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Executors"+ " | " + "Number of Executors : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadMessageStores(String rootDirPath)
    {
        Instant start = Instant.now();
        File messageStoresDir = new File(rootDirPath , MESSAGE_STORE_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Message Stores");
        int i = 0;
        if (messageStoresDir.exists()) {
            Iterator messageStores = FileUtils.iterateFiles(messageStoresDir, extensions, false);
            while (messageStores.hasNext()) {
                File file = (File) messageStores.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Message Stores"+ " | " + "Number of Message Stores : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadMessageProcessors(String rootDirPath)
    {
        Instant start = Instant.now();
        File messageProcessorDir = new File(rootDirPath , MESSAGE_PROCESSOR_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Message Processors");
        int i = 0;
        if (messageProcessorDir.exists()) {
            Iterator messageProcessors = FileUtils.iterateFiles(messageProcessorDir, extensions, false);
            while (messageProcessors.hasNext()) {
                File file = (File) messageProcessors.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Message Processors"+ " | " + "Number of Message Processors : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadInboundEndpoint(String rootDirPath)
    {
        Instant start = Instant.now();
        File inboundEndpointDir = new File(rootDirPath , INBOUND_ENDPOINT_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading Inbound Endpoints");
        int i = 0;
        if (inboundEndpointDir.exists()) {
            Iterator inboundEndpointIterator = FileUtils.iterateFiles(inboundEndpointDir, extensions, false);
            while (inboundEndpointIterator.hasNext()) {
                File file = (File) inboundEndpointIterator.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading Inbound Endpoints"+ " | " + "Number of Inbound Endpoints : " +i + " | "+ "Total Time : " + Duration.between(start, end).toMillis() +" ms");
    }
    private static void loadAPIs(String rootDirPath)
    {
        Instant start = Instant.now();
        File apiDir = new File(rootDirPath , REST_API_DIR);
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Start loading APIs");
        int i = 0;
        if (apiDir.exists()) {
            Iterator apiIterator = FileUtils.iterateFiles(apiDir, extensions, false);
            while (apiIterator.hasNext()) {
                File file = (File) apiIterator.next();
                Instant before = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Opening file | " +file.getName());
                try {
                    OMElement document = getOMElement(file);
                    
                } catch (Exception e) {
                    System.out.println("File cannot be build : " + file.getName());
                }
                Instant after = Instant.now();
                System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Closing file | " +file.getName() + " | " + Duration.between(before, after).toMillis() +" ms");
                i++;
            }
        }
        Instant end = Instant.now();
        System.out.println( "[" + getCurrentTimeStamp() +"]"+ " | " + "Done loading APIs"+ " | " + "Number of APIs : " +i + " | "+ "Total Time : "+ Duration.between(start, end).toMillis() +" ms" );
    }


    private static OMElement getOMElement(File file)
    {
        FileInputStream is;
        OMElement document = null;

        try {
            is = FileUtils.openInputStream(file);
        } catch (IOException e) {
            System.out.println("Error while opening the file: " + file.getName() + " for reading");
            return null;
        }

        try {
            document = new StAXOMBuilder(is).getDocumentElement();
            document.build();
            is.close();
        } catch (XMLStreamException e) {
            System.out.println("Error while parsing the content of the file: " + file.getName());
        } catch (IOException e) {
            System.out.println("Error while closing the input stream from the file: " + file.getName());
        }

        return document;
    }
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }


}