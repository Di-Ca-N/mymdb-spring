import {useState, useEffect} from 'react';
import { Link } from 'react-router-dom';

function App() {
  const [movies, setMovies] = useState([]);
  
  useEffect(() => {
    async function fetchMovies() {
      const response = await fetch("http://localhost:8080/movies", {method: "GET", mode: "cors"});
      if (response.ok) {
        const data = await response.json()
        setMovies(data["_embedded"]["movieList"]);
      }
    }
    fetchMovies();
  }, []);
  
  return (
    <main className="w-4/5 m-auto pt-5">
      <h2 className="text-lg font-bold">Filmes cadastrados!</h2>
      <ul>
        {movies.map(movie => (
          <li key={movie.title}>
            <Link to={`/movie/${movie.id}`}>{movie.title}</Link>
          </li>
        ))}
      </ul>
      
    </main>
  );
}

export default App;
