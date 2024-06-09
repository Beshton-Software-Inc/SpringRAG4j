package com.beshton.rag.springrag4j;

import com.azure.ai.openai.OpenAIClient;
import org.rag4j.applications.indexing.VasaWeaviateChunkClassBuilder;
import org.rag4j.indexing.IndexingService;
import org.rag4j.indexing.splitters.SentenceSplitter;
import org.rag4j.integrations.openai.OpenAIEmbedder;
import org.rag4j.integrations.openai.OpenAIFactory;
import org.rag4j.integrations.weaviate.WeaviateAccess;
import org.rag4j.integrations.weaviate.indexer.WeaviateChunkIndexer;
import org.rag4j.integrations.weaviate.retrieval.WeaviateRetriever;
import org.rag4j.integrations.weaviate.store.WeaviateContentStore;
import org.rag4j.rag.embedding.Embedder;
import org.rag4j.rag.retrieval.Retriever;
import org.rag4j.util.keyloader.KeyLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static org.rag4j.integrations.weaviate.WeaviateContants.CLASS_NAME;

@Service
public class IndexingtoWeaviateService {
    public void processFile(MultipartFile file) {
        // Initialize the components
        KeyLoader keyLoader = new KeyLoader();
        OpenAIClient openAIClient = OpenAIFactory.obtainsClient(keyLoader.getOpenAIKey());

        Embedder embedder = new OpenAIEmbedder(openAIClient);
        WeaviateAccess weaviateAccess = new WeaviateAccess(keyLoader);

        WeaviateChunkIndexer weaviateChunkIndexer = new WeaviateChunkIndexer(weaviateAccess);
        WeaviateContentStore contentStore = new WeaviateContentStore(weaviateChunkIndexer, embedder);
        IndexingService indexingService = new IndexingService(contentStore);
        Retriever retriever = new WeaviateRetriever(weaviateAccess, embedder);

        // Use the components
        createWeaviateSchema(weaviateAccess);
        indexingService.indexDocuments(new TextContentReader(file), new SentenceSplitter());
    }


    private static void createWeaviateSchema(WeaviateAccess weaviateAccess) {
        VasaWeaviateChunkClassBuilder WeaviateChunkClassBuilder = new VasaWeaviateChunkClassBuilder();
        weaviateAccess.forceCreateClass(WeaviateChunkClassBuilder.build());
        String schema = weaviateAccess.getSchemaForClass(CLASS_NAME);
        System.out.println(schema);
    }


}
