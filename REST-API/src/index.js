import app from "./app.js";
import { PORT } from "./configuration/configuration.js";

app.listen(PORT);

console.log(`Server listening on port ${PORT}`);
