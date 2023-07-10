import MovieAPI from './api/MovieAPI';

const movieAPI = new MovieAPI();

export const categories = [
    {
        name: "trending",
        title: "Em Alta",
        fetchMethod: movieAPI.getTrending,
        isLarge: true,
    },
    {
        name: "netflixOriginals",
        title: "Originais Netflix",
        fetchMethod: movieAPI.getNetflixOriginals,
        isLarge: false,
    },
    {
        name: "topRated",
        title: "Populares",
        fetchMethod: movieAPI.getTopRated,
        isLarge: false,
    },
    {
        name: "comedy",
        title: "Comédias",
        fetchMethod: movieAPI.getComedy,
        isLarge: false,
    },
    {
        name: "romances",
        title: "Romances",
        fetchMethod: movieAPI.getRomances,
        isLarge: false,
    },
    {
        name: "documentaries",
        title: "Documentários",
        fetchMethod: movieAPI.getDocumentaries,
        isLarge: false,
    }
]
