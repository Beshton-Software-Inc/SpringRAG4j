This is a RAG solution with Java. Typical architecture look like this:
![image](https://github.com/Beshton-Software-Inc/SpringRAG4j/assets/44378625/71411232-3d9e-4572-95dd-624c7834b481)

1. VectorDB there are many vendors for vector db. Milvus, Weaviate, Quadrant, Chroma. There are also hosted Pinecone, Postgres and Mongo also
   implemented vector solution.
2. Data ingress are typically using langchain https://www.langchain.com/ related packages. Easy to use Llamaindex https://www.llamaindex.ai/
   is a also popular solution. 
3. large language models are available. check the leaderboard on huggingface: https://huggingface.co/spaces/open-llm-leaderboard/open_llm_leaderboard
4. for embedding calculation, there are many popular models.
5. Large Language Models next hot area: Multi-model. 
