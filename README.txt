@authors:    David Gonzalez & Dillon Gonzalez
@date:       3/26/2026

How to run: From the top toolbar hover over "Run" and then click "Edit Configuration." From here you will need to
            select "Application" as an option and then set main class as "SimulatedScheduler" and add in the
            desired arguments. For example "FIFO 15 2 3 4 5" once this has been saved hover over "Run" again
            and choose your named configuration for execution.

Description: This is a simulated scheduler that simulates FIFO, SJF & Round-Robin.
             Round-Robin will utilize quantum numbers to ensure resource usage and
             prevent starvation.

Proj Scope: - Create a functional scheduler for SJF, FIFO & Round-Robin
            - Schedulers will use processes of "p1, p2, p3" as en example to work with.
            - Utilize quantum numbers for Round-Robin
            - Round-Robin COULD instead of using a fixed quantum use a dynamic time quantum. (Try fixed initially)
            - Project will focus on the algorithm & understanding of schedulers and not applying
                them to any real-word scenario in this project.


Further explanation on dynamic quantum time:

            For example, a small quantum can lead to a significant amount of context switches,
            while a large quantum can make Round-Robin behave more like FIFO. Utilizing a dynamic
            quantum can allow us to adjust the quantum based on the current state of the system.

            As a possible implementation we COULD average the burst time (a.k.a execution time)
            of our processes at certain intervals or whenever the ready queue changes to calculate
            the average of the remaining burst times for all processes currently in queue. This,
            calculated average would be the new time quantum for next scheduling cycle.
