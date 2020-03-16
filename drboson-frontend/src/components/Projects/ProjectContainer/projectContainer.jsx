import React from 'react';
import ProjectCard from '../ProjectCard/projectCard';

const ProjectContainer = (props) => {
    return (
        <div className="projects-container" style={{ backgroundColor: "#fbfbfb" }}>
            <div className="projects-header">
                <img className="pr-2"
                    alt=""
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAAQdJREFUSA1jYKAxYGRo3OTJ8P/vLIb/DDIYdjEyPGFgZE5jqPfbjiFHpAALyPBEffnnFrKCGBYcf/rh+YLzD2YBzZIl0jwMZUALGGSwGQ5SaSktYLrhOvvpD/Xr/2PoxCaAxccs2NQhi3W6aJoi8/GxsfkYxYKtt14ybL71nOE/ce7FZhfEMSAfQ32DYsGJp+8YJvkYMtgqiGLTTJLYphvPn9ftuTyLCVnXqy8/qWI4yEw/DUlTUPyiWIBsGbXYoxYQDMnRIBoNIoIhQFABzVMRCwMj4/+7H77+ZWJkZhHjYSfoImIVAAu708ASVZKRoWHjSgaGf6HAgomRWM1EqYNVPkQpHtGKAGyFTUB/QysiAAAAAElFTkSuQmCC"
                    srcSet="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAAQdJREFUSA1jYKAxYGRo3OTJ8P/vLIb/DDIYdjEyPGFgZE5jqPfbjiFHpAALyPBEffnnFrKCGBYcf/rh+YLzD2YBzZIl0jwMZUALGGSwGQ5SaSktYLrhOvvpD/Xr/2PoxCaAxccs2NQhi3W6aJoi8/GxsfkYxYKtt14ybL71nOE/ce7FZhfEMSAfQ32DYsGJp+8YJvkYMtgqiGLTTJLYphvPn9ftuTyLCVnXqy8/qWI4yEw/DUlTUPyiWIBsGbXYoxYQDMnRIBoNIoIhQFABzVMRCwMj4/+7H77+ZWJkZhHjYSfoImIVAAu708ASVZKRoWHjSgaGf6HAgomRWM1EqYNVPkQpHtGKAGyFTUB/QysiAAAAAElFTkSuQmCC 1x,data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAAAjpJREFUaAXtWc9LAkEUfiOWRoVUQmhCBHXqUBHmoQ7SsXOXOgQdu3WqW3mL+gP6ce0SRBBdOnsoiMKDHYIIokDcIFQkTdN0mxlXEdmNyWftEjOH8bk77833vm/eDswAyGYuA4RPHzr3AXzsUDsIKniEIBFQ6LgwgGMNQnMxIZ9fGESgAj5Kgfc2FZ9AkiYxZlYSds48Be9ytt+sz4z4+jrahBRI5IrK9sVDLJ0v+IFw9RabIgDpZKP+QRbjJ+DZeJYo82E2bUHem9DZq2telPl6jMyH2EBRy7RuNk/V+ndoW7DGmAKotjwxFGNJoILoObOPiQoLdIlHK3WqNwiA1gCuBbwuf8A7jgui4y1aY2gFdOZuySPRGvtWgadUDg4ij5DKF0Ft7QoXTbLyRWTLqVpjDbVhqAADv3V5D8mcaeD1k2yoDUMFruJ0f6KsTw+6YWN2FPq7HPoB//ip8pZXlk6uY6+ZPN9/DBW4fUlzaFYCzwB5up2ew/mp2v5jmEDivcATsArzHIzWsSS4SZeTYQL1Dla2ZQJmqyMVkAogGZBLCEkg2l0qgKYQGUAqgCQQ7S4VQFOIDCAVQBKIdpcKoClEBpAKIAlEu0sF0BQiA/wfBbLFUo2LTKFiuzutcRpXA6YZ7HSOm/Sc1AYEcuzPfuQ5k/0sQbZYhuO7OH8/OdDDf63UVY8WNUxhesl3tgtqecVKIIWwaJeLtAaGV4HY9oAQroSQs5mD2PE6gSMzb0bNTF/OLRmQDFiNgS/x6qefKI9AogAAAABJRU5ErkJggg== 2x" />
                Projects
            </div>

            <div className="projects row">
                <ProjectCard />
                <ProjectCard />
                <ProjectCard />
                <ProjectCard />
            </div>
        </div>
    );
}

export default ProjectContainer;